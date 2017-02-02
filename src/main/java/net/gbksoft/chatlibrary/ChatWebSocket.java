package net.gbksoft.chatlibrary;


import android.os.Handler;
import android.os.Looper;

import net.gbksoft.chatlibrary.chatdata.ChatDataListeners;
import net.gbksoft.chatlibrary.connection.ChatConnectionListener;
import net.gbksoft.chatlibrary.log.IChatLogListener;
import net.gbksoft.chatlibrary.log.ChatLogPresenter;
import net.gbksoft.chatlibrary.model.output.AddUser;
import net.gbksoft.chatlibrary.model.output.BlockGroup;
import net.gbksoft.chatlibrary.model.output.ChangeUsersRoles;
import net.gbksoft.chatlibrary.model.output.EditMessage;
import net.gbksoft.chatlibrary.model.output.GetUsers;
import net.gbksoft.chatlibrary.model.output.Group;
import net.gbksoft.chatlibrary.model.output.Message;
import net.gbksoft.chatlibrary.model.output.ReadMessage;
import net.gbksoft.chatlibrary.model.output.RestrictUser;
import net.gbksoft.chatlibrary.model.output.UnreadSenders;
import net.gbksoft.chatlibrary.request.ChatFileRequest;
import net.gbksoft.chatlibrary.request.ChatHistoryRequest;
import net.gbksoft.chatlibrary.request.ChatRequest;
import net.gbksoft.chatlibrary.utils.SSLUtils;

import org.json.JSONObject;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 *
 * Main Class of Chat Library
 * Contains public methods for working with sockets.
 */

public class ChatWebSocket {
    static final String TAG = "ChatWebSocket";

    private static final String USER_IS_WRITING = "userIsWriting";

    public static final String REQUEST_FOR_BAN = "requestForBan";
    public static final String REQUEST_FOR_UNBAN = "requestForUnban";
    public static final String REQUEST_FOR_BLOCK_GROUP = "requestForBlockGroup";
    public static final String REQUEST_FOR_UNBLOCK_GROUP = "requestForUnblockGroup";
    public static final String REQUEST_FOR_JOIN = "requestForJoin";

    //output events
    private static final String SEND_MESSAGE = "sendMessage";
    private static final String SET_READED = "setReaded";
    private static final String GET_UNREAD = "getUnread";
    private static final String CREATE_GROUP = "createGroup";
    private static final String CHANGE_GROUP = "changeGroup";
    private static final String ADD_USERS = "addUser";
    private static final String GET_USERS = "getUsers";
    private static final String OPEN_CONVERSATION = "openConversation";
    private static final String CLOSE_CONVERSATION = "closeConversation";
    private static final String SET_USER_STATUS = "setUserStatus";
    private static final String CHANGE_USERS_ROLES = "changeUsersRoles";
    private static final String RESTRICT_USERS = "restrictUsers";
    private static final String DELETE_MESSAGE = "deleteMessage";
    private static final String BLOCK_GROUP = "blockGroup";
    private static final String UNBLOCK_GROUP = "unblockGroup";
    private static final String EDIT_MESSAGE = "editMessage";

    public static final String GET_HISTORY = "getHistory";
    public static final String GET_HISTORY_SPECIAL = "getHistorySpecial";
    public static final String STORE_FILE = "storeFile";
    public static final String REMOVE_FILE = "removeFile";
    public static final String SET_REQUEST_STATUS = "setMessageStatus";

    //input events
    private static final String CONNECTED = "connected";
    private static final String MESSAGE_USER = "messageUser";
    private static final String MESSAGE_GROUP = "messageGroup";
    private static final String MESSAGE_BROADCAST = "messageBroadcast";
    private static final String JOINED_TO = "joinedTo";
    private static final String HISTORY = "history";
    private static final String HISTORY_SPECIAL = "historySpecial";
    private static final String UNREAD = "unread";
    private static final String NEW_GROUP = "newGroup";
    private static final String USER_IN = "userIn";
    private static final String USER_OUT = "userOut";
    private static final String USERS = "users";
    private static final String MESSAGES_ARE_READ = "messagesAreRead";
    private static final String MESSAGES_IS_SENT = "messageIsSent";
    private static final String USER_STATUS = "userStatus";
    private static final String RESTRICTED_RIGHTS = "restrictedRights";
    private static final String NEW_ROLE = "newRole";
    private static final String MESSAGE_IS_DELETED = "messageIsDeleted";
    private static final String MESSAGE_IS_EDITED = "messageIsEdited";
    private static final String GROUP_IS_BLOCKED = "groupIsBlocked";
    private static final String GROUP_IS_UNBLOCKED = "groupIsUnblocked";
    private static final String REQUEST_ACCEPTED = "requestAccepted";
    private static final String FILE_IS_STORED = "fileIsStored";
    private static final String REQUEST_STATUS_IS_CHANGED = "messageStatusIsChanged";
    private static final String MESSAGE_ADD = "messageAdd";
    private static final String FILE_IS_REMOVED = "fileIsRemoved";
    private static final String SERVER_ERROR = "serverError";
    private static final String AUTH_ERROR = "authError";

//    private static ChatWebSocket mChatWebSocket;
    private Socket mSocket;
    private ChatWebSocketBuilder mBuilder;
    private Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    private Handler mUserIsWritingHandler = new Handler();

    private ChatLogPresenter mChatLogPresenter;
    private ChatConnectionPresenter mChatConnectionPresenter;
    private ChatDataPresenter mChatDataPresenter;
    private ChatMessageSendPresenter mChatMessageSendPresenter;
    private ChatInputPresenter mChatInputPresenter;

    private IChatWebSocketRequestListener mRequestListener;

    private int userIsWritingRepeatTime = 5000;

    public interface IChatWebSocketRequestListener {
        void createRequest(String event, Object... args);
    }

    private ChatWebSocket() {

    }

    ChatWebSocket(ChatWebSocketBuilder builder) {
        mBuilder = builder;

        mChatConnectionPresenter = new ChatConnectionPresenter();
        mChatDataPresenter = new ChatDataPresenter();
        mChatLogPresenter = new ChatLogPresenter();
        mChatMessageSendPresenter = new ChatMessageSendPresenter(this);

        mChatInputPresenter = new ChatInputPresenter(mChatLogPresenter, mChatConnectionPresenter,
                mChatDataPresenter, mChatMessageSendPresenter);

        mRequestListener = this::sendEvent;
    }

    /**
     * Connect to the server.
     *
     * @param authKey auth code from API to connect
     */
    public void connect(String authKey) throws KeyManagementException, NoSuchAlgorithmException {

        if (isConnected()) {
            return;
        }

        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        opts.reconnection = true;
        opts.sslContext = SSLUtils.getUnsafeSSL();
        opts.query = "token=" + authKey;

        try {
            String uri = String.format("%s://%s:%s%s", mBuilder.getChatFrontScheme(),
                    mBuilder.getChatFrontPath(), mBuilder.getPort(),
                    mBuilder.getChatSocketNamespace());

            mSocket = IO.socket(uri, opts);

            mSocket.on(CONNECTED, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onConnected((JSONObject)args[0])
                );
            });

            mSocket.on(MESSAGE_USER, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onMessageUser((JSONObject)args[0])
                );
            });

            mSocket.on(MESSAGE_GROUP, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onMessageGroup((JSONObject)args[0])
                );
            });

            mSocket.on(MESSAGE_BROADCAST, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onMessageBroadcast((JSONObject)args[0])
                );
            });

            mSocket.on(JOINED_TO, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onJoinedTo((JSONObject)args[0])
                );
            });

            mSocket.on(HISTORY, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onHistory((JSONObject)args[0])
                );
            });

            mSocket.on(UNREAD, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onUnread((JSONObject)args[0])
                );
            });

            mSocket.on(NEW_GROUP, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onNewGroup((JSONObject)args[0])
                );
            });

            mSocket.on(USER_IN, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onUserIn((JSONObject)args[0])
                );
            });

            mSocket.on(USER_OUT, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onUserOut((JSONObject)args[0])
                );
            });

            mSocket.on(USERS, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onGroupUsers((JSONObject)args[0])
                );
            });

            mSocket.on(USER_IS_WRITING, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onUserIsWriting((JSONObject)args[0])
                );
            });

            mSocket.on(MESSAGES_IS_SENT, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onMessagesIsSent((JSONObject)args[0])
                );
            });

            mSocket.on(MESSAGES_ARE_READ, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onMessagesAreRead((JSONObject)args[0])
                );
            });

            mSocket.on(USER_STATUS, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onUserStatus((JSONObject)args[0])
                );
            });

            mSocket.on(RESTRICTED_RIGHTS, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onRestrictedRights((JSONObject)args[0])
                );
            });

            mSocket.on(NEW_ROLE, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onChangeUserRoles((JSONObject)args[0])
                );
            });

            mSocket.on(MESSAGE_IS_DELETED, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onMessageIsDeleted((JSONObject)args[0])
                );
            });

            mSocket.on(MESSAGE_IS_EDITED, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onMessageIsEdited((JSONObject)args[0])
                );
            });

            mSocket.on(GROUP_IS_BLOCKED, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onBlockGroup((JSONObject)args[0])
                );
            });

            mSocket.on(GROUP_IS_UNBLOCKED, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onUnblockGroup((JSONObject)args[0])
                );
            });

            mSocket.on(REQUEST_ACCEPTED, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onRequestAccepted((JSONObject)args[0])
                );
            });

            mSocket.on(REQUEST_FOR_BAN, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onRequestForBan((JSONObject) args[0])
                );
            });

            mSocket.on(REQUEST_FOR_UNBAN, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onRequestForUnban((JSONObject) args[0])
                );
            });

            mSocket.on(REQUEST_FOR_BLOCK_GROUP, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onRequestForBlockGroup((JSONObject) args[0])
                );
            });

            mSocket.on(REQUEST_FOR_UNBLOCK_GROUP, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onRequestForUnblockGroup((JSONObject) args[0])
                );
            });

            mSocket.on(REQUEST_FOR_JOIN, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onRequestForJoin((JSONObject) args[0])
                );
            });

            mSocket.on(HISTORY_SPECIAL, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onHistorySpecial((JSONObject) args[0])
                );
            });

            mSocket.on(FILE_IS_STORED, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onFileIsStored((JSONObject)args[0])
                );
            });

            mSocket.on(REQUEST_STATUS_IS_CHANGED, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onRequestStatusIsChanged((JSONObject)args[0])
                );
            });

            mSocket.on(MESSAGE_ADD, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onMessageAdd((JSONObject)args[0])
                );
            });

            mSocket.on(FILE_IS_REMOVED, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onFileIsRemoved(args[0])
                );
            });

            mSocket.on(SERVER_ERROR, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onServerError((JSONObject)args[0])
                );
            });

            mSocket.on(AUTH_ERROR, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onAuthTokenError((JSONObject)args[0])
                );
            });

            //base Socket status callback
            mSocket.on(Socket.EVENT_CONNECT, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onEventConnect(args)
                );
            });

            mSocket.on(Socket.EVENT_DISCONNECT, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onEventDisconnect(args[0])
                );
            });

            mSocket.on(Socket.EVENT_RECONNECT, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onEventReconnect(args[0])
                );
            });

            mSocket.on(Socket.EVENT_CONNECT_ERROR, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onEventConnectError(args[0])
                );
            });

            mSocket.on(Socket.EVENT_ERROR, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onEventError(args[0])
                );
            });

            mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, (args) -> {
                mMainThreadHandler.post(() ->
                        mChatInputPresenter.onEventConnectTimeout(args[0])
                );
            });

            mSocket.connect();

        } catch (URISyntaxException e) {
            mChatLogPresenter.e(TAG, e.getMessage(), e);
        }
    }

    /**
     * Add user`s to room
     *
     * @param groupId is a group id, in which users need to be added.
     * @param userIds List of users
     */
    public void addUserToClosedGroup(String groupId, ArrayList<String> userIds) {
        sendEvent(ADD_USERS, new AddUser(groupId, userIds).toJSONObject());
    }

    /**
     * Change user`s room
     *
     * @param groupId is a group id, in which user need to enter.
     */
    public void changeGroup(String groupId) {
        sendEvent(CHANGE_GROUP, groupId);
    }

    /**
     * Create private user`s room
     *
     * @param name Name of a group
     * @param userIds List of users
     * @param needBadges flag for unread messages
     */
    public void createPrivateGroup(String name, ArrayList<String> userIds, boolean needBadges) {
        createGroup(new Group(name, userIds, needBadges, Group.GROUP_CLOSED_TYPE));
    }

    /**
     * Create public user`s room
     *
     * @param name Name of a group
     */
    public void createPublicGroup(String name) {
        createGroup(new Group(name, null, false, Group.GROUP_OPEN_TYPE));
    }

    /**
     * Create secret user`s room
     *
     * @param name Name of a group
     * @param userIds List of users
     * @param needBadges flag for unread messages
     */
    public void createSecretGroup(String name, ArrayList<String> userIds, boolean needBadges) {
        createGroup(new Group(name, userIds, needBadges, Group.GROUP_SECRET_TYPE));
    }

    /**
     * Create user`s room
     */
    public void createGroup(Group group) {
        sendEvent(CREATE_GROUP, group.toJSONObject());
    }

    /**
     * Makes
     */
    public ChatHistoryRequest createHistoryRequest() {
        return new ChatHistoryRequest(mRequestListener);
    }

    /**
     * Send user`s message
     *
     * @param message message to send
     */
    public Message sendMessage(Message message) {
        return mChatMessageSendPresenter.sendMessage(message);
    }

    void sendMessageEvent(Message message) {
        sendEvent(SEND_MESSAGE, message.toJSONObject());
    }

    /**
     * Get unread messages
     *
     * @param ids id`s of users, that required unread messages
     */
    public void getUnread(String[] ids) {
        getUnread(new ArrayList<>(Arrays.asList(ids)));
    }

    /**
     * Get unread messages
     *
     * @param ids list of user`s id, that required unread messages
     */
    public void getUnread(ArrayList<String> ids) {
        sendEvent(GET_UNREAD, new UnreadSenders(ids).toJSONObject());
    }

    /**
     * Read message
     *
     * @param messageId id of the message that required set there flag to read.
     */
    public void setReaded(String senderId, String messageId) {
        ArrayList<String> messageIds = new ArrayList<>();
        messageIds.add(messageId);
        sendEvent(SET_READED, new ReadMessage(senderId, messageIds).toJSONObject());
    }

    /**
     * Read message
     *
     * @param messageIds id of the messages that required set there flag to read.
     */
    public void setReaded(String senderId, ArrayList<String> messageIds) {
        sendEvent(SET_READED, new ReadMessage(senderId, messageIds).toJSONObject());
    }

    /**
     * Read message
     *
     * @param senderId id of the user which chat required set there flag to read.
     */
    public void setReadedAll(String senderId) {
        sendEvent(SET_READED, new ReadMessage(senderId).toJSONObject());
    }

    /**
     * Get all user`s in the server
     *
     */
    public void getAllUsers() {
        sendEvent(GET_USERS, new GetUsers().toJSONObject());
    }

    /**
     * Get user`s in group
     *
     * @param groupId id of a room
     */
    public void getInGroupUsers(String groupId) {
        sendEvent(GET_USERS, new GetUsers(groupId, GetUsers.IN_GROUP).toJSONObject());
    }

    /**
     * Get online user`s out of group
     *
     * @param groupId id of a room
     */
    public void getNotInGroupUsers(String groupId) {
        sendEvent(GET_USERS, new GetUsers(groupId, GetUsers.NOT_IN_GROUP).toJSONObject());
    }

    /**
     * Get all user`s out of group
     *
     * @param groupId id of a room
     */
    public void getAllNotInGroupUsers(String groupId) {
        sendEvent(GET_USERS, new GetUsers(groupId, GetUsers.ALL_NOT_IN_GROUP).toJSONObject());
    }

    /**
     * Send user writing status to recipient
     *
     * @param recipientId user recepient id
     */
    public void setUserIsWritingStatus(String recipientId) {
        if (!mUserIsWritingHandler.hasMessages(0)) {
            sendEvent(USER_IS_WRITING, recipientId);
            mUserIsWritingHandler.postDelayed(() ->
                    sendEvent(USER_IS_WRITING, recipientId), userIsWritingRepeatTime);
        }
    }

    /**
     * Open conversation
     *
     * @param companionId user id to start chat with
     */
    public void openConversation(String companionId) {
        sendEvent(OPEN_CONVERSATION, companionId);
    }

    /**
     * Close conversation
     *
     * @param companionId user id to start chat with
     */
    public void closeConversation(String companionId) {
        sendEvent(CLOSE_CONVERSATION, companionId);
    }

    /**
     * Set user status
     *
     * @param status sets user status (See UserStatusType for details)
     */
    public void setUserStatus(String status) {
        sendEvent(SET_USER_STATUS, status);
    }

    /**
     * Ban selected user
     *
     * @param groupId id of a room
     * @param usersId id of user, that need to be banned
     * @param period final date of userBar in millisec. If < 0 - than forever. 0 - if need remove a ban
     */
    public void banUser(String groupId, String usersId, long period) {
        ArrayList<String> usersIds = new ArrayList<>();
        usersIds.add(usersId);
        restrictUsers(groupId, usersIds, period);
    }

    /**
     * Ban selected users
     *
     * @param groupId id of a room
     * @param usersIds id of users, that need to be banned
     * @param period final date of userBar in millisec. If < 0 - than forever. 0 - if need remove a ban
     */
    public void banUsers(String groupId, ArrayList<String> usersIds, long period) {
        restrictUsers(groupId, usersIds, period);
    }

    /**
     * Unban selected user
     *
     * @param groupId id of a room
     * @param usersId id of user, that need to be removed a ban
     */
    public void unbanUser(String groupId, String usersId) {
        ArrayList<String> usersIds = new ArrayList<>();
        usersIds.add(usersId);
        restrictUsers(groupId, usersIds, 0);
    }

    /**
     * Unban selected users
     *
     * @param groupId id of a room
     * @param usersIds id of users, that need to be removed a ban
     */
    public void unbanUsers(String groupId, ArrayList<String> usersIds) {
        restrictUsers(groupId, usersIds, 0);
    }

    /**
     * Ban/unban selected users
     *
     * @param groupId id of a room
     * @param usersIds id of users, that need to be banned
     * @param period final date of userBar in millisec. If < 0 - than forever. 0 - if need remove a ban
     */
    private void restrictUsers(String groupId, ArrayList<String> usersIds, long period) {
        sendEvent(RESTRICT_USERS, new RestrictUser(groupId, usersIds, period).toJSONObject());
    }

    /**
     * Change user role
     *
     * @param groupId id of a room
     * @param usersId id of user, that role need to be changed
     * @param role standart role from @UserRoles.class
     */
    public void changeUserRole(String groupId, String usersId, String role) {
        ArrayList<String> usersIds = new ArrayList<>();
        usersIds.add(usersId);
        sendEvent(CHANGE_USERS_ROLES, new ChangeUsersRoles(groupId, usersIds, role).toJSONObject());
    }

    /**
     * Change user role
     *
     * @param groupId id of a room
     * @param usersIds id of users, that roles need to be changed
     * @param role standart role from @UserRoles.class
     */
    public void changeUsersRole(String groupId, ArrayList<String> usersIds, String role) {
        sendEvent(CHANGE_USERS_ROLES, new ChangeUsersRoles(groupId, usersIds, role).toJSONObject());
    }

    /**
     * Edit message
     *
     * @param messageId - id of the message
     * @param messageText - new text
     */
    public void editMessage(String messageId, String messageText) {
        sendEvent(EDIT_MESSAGE, new EditMessage(messageId, messageText).toJSONObject());
    }

    /**
     * Delete message by id
     */
    public void deleteMessage(String messageId) {
        sendEvent(DELETE_MESSAGE, messageId);
    }

    /**
     * Block group by id
     *
     * @param groupId - id of the group
     * @param reason - reason of the block
     */
    public void blockGroup(String groupId, String reason) {
        sendEvent(BLOCK_GROUP, new BlockGroup(groupId, reason).toJSONObject());
    }

    /**
     * Unblock group
     *
     * @param groupId - id of the group
     * @param reason - reason of the unblock
     */
    public void unblockGroup(String groupId, String reason) {
        sendEvent(UNBLOCK_GROUP, new BlockGroup(groupId, reason).toJSONObject());
    }


    /**
     * Creates request to manage requests for ban, unban user, block, unblock, join room and
     * approve or disapprove these requests
     *
     * @return {@link ChatRequest} object
     */

    public ChatRequest createRequest() {
        return new ChatRequest(mRequestListener);
    }


    /**
     * Creates request to manage file operations: upload images, video and audio files, also delete
     * them.
     *
     * @return {@link ChatFileRequest} object
     */

    public ChatFileRequest createFileRequest() {
        return new ChatFileRequest(mRequestListener);
    }

    /**
     * Check connection status
     *
     * @return connection status
     */
    public boolean isConnected() {
        return mSocket != null && mSocket.connected();
    }

    /**
     * Emits an event is connected and show event log.
     *
     * @param event an event name.
     * @param args data to send.
     */
    private void sendEvent(String event, Object... args) {
        mChatLogPresenter.i(TAG, event + " / " + (args != null ? args[0].toString() : "null"));
        if (isConnected()) {
            mSocket.emit(event, args);
        }
    }

    /**
     * Disconnect from the server.
     */
    public void disconnect() {
        if (mSocket != null) {
            mSocket.disconnect();
            mSocket.off();
            mSocket = null;
        }
    }

    /**
     * Set connection listener
     *
     * @param listener ISocketConnectionListener
     */
    public void setSocketConnectionListener(
            ChatConnectionListener.ISocketConnectionListener listener) {
        mChatConnectionPresenter.setSocketConnectionListener(listener);
    }

    /**
     * Remove connection listener
     */
    public void removeSocketConnectionListener() {
        mChatConnectionPresenter.removeSocketConnectionListener();
    }

    /**
     * Add data listener
     *
     * @param chatDataListener ChatDataListener
     */
    public void addChatDataListener(ChatDataListeners.IChatDataListener chatDataListener) {
        mChatDataPresenter.addChatStatusListener(chatDataListener);
    }

    /**
     * Remove data listener
     * *
     * @param chatDataListener ChatDataListener
     */
    public void removeChatDataListener(ChatDataListeners.IChatDataListener chatDataListener) {
        mChatDataPresenter.removeChatStatusListener(chatDataListener);
    }

    /**
     * Add log listener
     *
     * @param chatLogListener ChatDataListener
     */
    public void setLogListener(IChatLogListener chatLogListener) {
        mChatLogPresenter.setChatLogListener(chatLogListener);
    }

    /**
     * Remove log listener
     */
    public void removeLogListener() {
        mChatLogPresenter.removeLogListener();
    }


    /**
     * Sets user "is user writing" status delay in milliseconds
     * @param milliseconds int number of milliseconds
     */
    public void setUserIsWritingRepeatTime(int milliseconds) {
        userIsWritingRepeatTime = milliseconds;
    }
}