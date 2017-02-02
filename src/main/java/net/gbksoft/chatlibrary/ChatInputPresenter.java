package net.gbksoft.chatlibrary;

import com.google.gson.Gson;

import net.gbksoft.chatlibrary.log.ChatLogPresenter;
import net.gbksoft.chatlibrary.model.input.FileIsStored;
import net.gbksoft.chatlibrary.model.input.GroupIsBlocked;
import net.gbksoft.chatlibrary.model.input.GroupIsUnblocked;
import net.gbksoft.chatlibrary.model.input.GroupRequest;
import net.gbksoft.chatlibrary.model.input.GroupUsers;
import net.gbksoft.chatlibrary.model.input.History;
import net.gbksoft.chatlibrary.model.input.HistorySpecial;
import net.gbksoft.chatlibrary.model.input.JoinedGroup;
import net.gbksoft.chatlibrary.model.input.Message;
import net.gbksoft.chatlibrary.model.input.MessageAdd;
import net.gbksoft.chatlibrary.model.input.MessageIsDeleted;
import net.gbksoft.chatlibrary.model.input.MessageIsEdited;
import net.gbksoft.chatlibrary.model.input.NewRole;
import net.gbksoft.chatlibrary.model.input.RequestAccepted;
import net.gbksoft.chatlibrary.model.input.RequestStatus;
import net.gbksoft.chatlibrary.model.input.RestrictedRights;
import net.gbksoft.chatlibrary.model.input.UnreadMessages;
import net.gbksoft.chatlibrary.model.input.UserConnectionIn;
import net.gbksoft.chatlibrary.model.input.UserConnectionOut;
import net.gbksoft.chatlibrary.model.input.UserData;
import net.gbksoft.chatlibrary.model.input.UserRequest;
import net.gbksoft.chatlibrary.model.input.errors.ChatAddUserError;
import net.gbksoft.chatlibrary.model.input.errors.ChatAuthTokenError;
import net.gbksoft.chatlibrary.model.input.errors.ChatBlockGroupError;
import net.gbksoft.chatlibrary.model.input.errors.ChatChangeGroupError;
import net.gbksoft.chatlibrary.model.input.errors.ChatChangeUsersRolesError;
import net.gbksoft.chatlibrary.model.input.errors.ChatCheckUnreadError;
import net.gbksoft.chatlibrary.model.input.errors.ChatCreateGroupError;
import net.gbksoft.chatlibrary.model.input.errors.ChatDeleteMessageError;
import net.gbksoft.chatlibrary.model.input.errors.ChatEditMessageError;
import net.gbksoft.chatlibrary.model.input.errors.ChatGetHistoryError;
import net.gbksoft.chatlibrary.model.input.errors.ChatGetUsersError;
import net.gbksoft.chatlibrary.model.input.errors.ChatRemoveFileError;
import net.gbksoft.chatlibrary.model.input.errors.ChatRestrictUsersError;
import net.gbksoft.chatlibrary.model.input.errors.ChatSendMessageError;
import net.gbksoft.chatlibrary.model.input.errors.ChatSendToAllError;
import net.gbksoft.chatlibrary.model.input.errors.ChatSendToGroupError;
import net.gbksoft.chatlibrary.model.input.errors.ChatSendToUserError;
import net.gbksoft.chatlibrary.model.input.errors.ChatServerError;
import net.gbksoft.chatlibrary.model.input.errors.ChatServerUnknownError;
import net.gbksoft.chatlibrary.model.input.errors.ChatSetReadedError;
import net.gbksoft.chatlibrary.model.input.errors.ChatSetRequestStatusError;
import net.gbksoft.chatlibrary.model.input.errors.ChatSetUserStatusError;
import net.gbksoft.chatlibrary.model.input.errors.ChatStoreFileError;
import net.gbksoft.chatlibrary.model.input.errors.ChatUnblockGroupError;
import net.gbksoft.chatlibrary.model.input.errors.ChatUndefinedError;
import net.gbksoft.chatlibrary.model.input.errors.ChatUserIsWritingError;
import net.gbksoft.chatlibrary.model.input.status.ReadStatus;
import net.gbksoft.chatlibrary.model.input.status.SendStatus;
import net.gbksoft.chatlibrary.model.input.status.UserIsWriting;
import net.gbksoft.chatlibrary.model.input.status.UserStatus;

import org.json.JSONObject;

/**
 *
 * Main presenter for {@link ChatWebSocket} class.
 * Contains methods for all input WebSocket data
 */

class ChatInputPresenter {
    private Gson mGson = new Gson();

    private ChatLogPresenter mChatLogPresenter;
    private ChatConnectionPresenter mChatConnectionPresenter;
    private ChatDataPresenter mChatDataPresenter;
    private ChatMessageSendPresenter mChatMessageSendPresenter;

    ChatInputPresenter(ChatLogPresenter chatLogPresenter,
           ChatConnectionPresenter chatConnectionPresenter,
           ChatDataPresenter chatDataPresenter, ChatMessageSendPresenter chatMessageSendPresenter) {

        mChatLogPresenter = chatLogPresenter;
        mChatConnectionPresenter = chatConnectionPresenter;
        mChatDataPresenter = chatDataPresenter;
        mChatMessageSendPresenter = chatMessageSendPresenter;
    }

    void onConnected(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_CONNECTED " + jsonObject);
        mChatDataPresenter.onConnected(mGson.fromJson(jsonObject.toString(), UserData.class));
    }

    void onMessageUser(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_MESSAGE_USER" + jsonObject);
        mChatDataPresenter.onMessageUserReceived(observeMessage(jsonObject));
    }

    void onMessageGroup(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_MESSAGE_GROUP" + jsonObject);
        mChatDataPresenter.onMessageGroupReceived(observeMessage(jsonObject));
    }

    void onMessageBroadcast(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_MESSAGE_BROADCAST" + jsonObject);
        mChatDataPresenter.onMessageBroadcastReceived(observeMessage(jsonObject));
    }

    private Message observeMessage(JSONObject jsonObject) {
        Message inputMessage = mGson.fromJson(
                jsonObject.toString(),
                net.gbksoft.chatlibrary.model.input.Message.class);

        mChatMessageSendPresenter.onNewInputMessage(inputMessage);
        return inputMessage;
    }

    void onJoinedTo(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_JOINED_TO " + jsonObject);
        mChatDataPresenter.onJoinedTo(mGson.fromJson(jsonObject.toString(), JoinedGroup.class));
    }

    void onHistory(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_HISTORY" + jsonObject);
        mChatDataPresenter.onHistoryData(mGson.fromJson(jsonObject.toString(), History.class));
    }

    void onUnread(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_UNREAD" + jsonObject);
        mChatDataPresenter.onUnreadMessages(
                mGson.fromJson(jsonObject.toString(),
                UnreadMessages.class));
    }

    void onNewGroup(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_NEW_GROUP" + jsonObject);
        mChatDataPresenter.onNewGroup(
                mGson.fromJson(jsonObject.toString(),
                net.gbksoft.chatlibrary.model.input.Group.class));
    }

    void onUserIn(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_USER_IN " + jsonObject);
        mChatDataPresenter.onUserIn(mGson.fromJson(jsonObject.toString(), UserConnectionIn.class));
    }

    void onUserOut(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_USER_OUT" + jsonObject);
        mChatDataPresenter.onUserOut(
                mGson.fromJson(jsonObject.toString(),
                UserConnectionOut.class));
    }

    void onGroupUsers(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_USERS" + jsonObject);
        mChatDataPresenter.onGroupUsers(mGson.fromJson(jsonObject.toString(), GroupUsers.class));
    }

    void onUserIsWriting(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_USER_IS_WRITING" + jsonObject);
        mChatDataPresenter.onUserIsWriting(
                mGson.fromJson(jsonObject.toString(),
                UserIsWriting.class).getUser());
    }

    void onMessagesIsSent(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_MESSAGES_IS_SENT" + jsonObject);
        mChatDataPresenter.onMessagesIsSent(
                mGson.fromJson(jsonObject.toString(),
                SendStatus.class));
    }

    void onMessagesAreRead(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_MESSAGES_ARE_READ" + jsonObject);
        mChatDataPresenter.onMessagesAreRead(
                mGson.fromJson(jsonObject.toString(),
                ReadStatus.class));
    }

    void onUserStatus(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_USER_STATUS" + jsonObject);
        mChatDataPresenter.onUserStatus(mGson.fromJson(jsonObject.toString(), UserStatus.class));
    }

    void onChangeUserRoles(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_CHANGE_USER_ROLES" + jsonObject);
        mChatDataPresenter.onChangeUserRoles(mGson.fromJson(jsonObject.toString(), NewRole.class));
    }

    void onRestrictedRights(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_RESTRICTED_RIGHTS" + jsonObject);
        mChatDataPresenter.onRestrictedRights(
                mGson.fromJson(jsonObject.toString(),
                RestrictedRights.class));
    }

    void onMessageIsDeleted(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_MESSAGE_IS_DELETED" + jsonObject);
        mChatDataPresenter.onMessageIsDeleted(
                mGson.fromJson(jsonObject.toString(),
                MessageIsDeleted.class));
    }

    void onMessageIsEdited(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_MESSAGE_IS_EDITED" + jsonObject);
        mChatDataPresenter.onMessageIsEdited(
                mGson.fromJson(jsonObject.toString(),
                MessageIsEdited.class));
    }

    void onBlockGroup(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_BLOCK_GROUP" + jsonObject);
        mChatDataPresenter.onBlockGroup(
                mGson.fromJson(jsonObject.toString(),
                GroupIsBlocked.class));
    }

    void onUnblockGroup(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_UNBLOCK_GROUP" + jsonObject);
        mChatDataPresenter.onUnblockGroup(
                mGson.fromJson(jsonObject.toString(),
                GroupIsUnblocked.class));
    }

    void onRequestAccepted(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_REQUEST_ACCEPTED" + jsonObject);
        mChatDataPresenter.onRequestAccepted(
                mGson.fromJson(jsonObject.toString(),
                RequestAccepted.class));
    }

    void onRequestForBan(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_REQUEST_FOR_BAN" + jsonObject);
        mChatDataPresenter.onRequestForBan(
                mGson.fromJson(jsonObject.toString(),
                UserRequest.class));
    }

    void onRequestForUnban(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_REQUEST_FOR_UNBAN" + jsonObject);
        mChatDataPresenter.onRequestForUnban(
                mGson.fromJson(jsonObject.toString(),
                UserRequest.class));
    }

    void onRequestForBlockGroup(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_REQUEST_FOR_BLOCK_GROUP" + jsonObject);
        mChatDataPresenter.onRequestForBlockGroup(
                mGson.fromJson(jsonObject.toString(),
                GroupRequest.class));
    }

    void onRequestForUnblockGroup(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_REQUEST_FOR_UNBLOCK_GROUP" + jsonObject);
        mChatDataPresenter.onRequestForUnblockGroup(
                mGson.fromJson(jsonObject.toString(),
                GroupRequest.class));
    }

    void onRequestForJoin(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_REQUEST_FOR_JOIN" + jsonObject);
        mChatDataPresenter.onRequestForJoin(
                mGson.fromJson(jsonObject.toString(),
                GroupRequest.class));
    }

    void onHistorySpecial(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_HISTORY_SPECIAL" + jsonObject);
        mChatDataPresenter.onHistorySpecial(
                mGson.fromJson(jsonObject.toString(),
                HistorySpecial.class));
    }

    void onFileIsStored(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_FILE_IS_STORED" + jsonObject);
        mChatDataPresenter.onFileIsStored(
                mGson.fromJson(jsonObject.toString(),
                FileIsStored.class));
    }

    void onRequestStatusIsChanged(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_REQUEST_STATUS_IS_CHANGED " + jsonObject);
        mChatDataPresenter.onRequestStatusIsChanged(
                mGson.fromJson(jsonObject.toString(),
                RequestStatus.class));
    }

    void onMessageAdd(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_MESSAGE_ADD" + jsonObject);
        mChatDataPresenter.onMessageAdd(
                mGson.fromJson(jsonObject.toString(),
                MessageAdd.class));
    }

    void onFileIsRemoved(Object object) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_FILE_IS_REMOVED " + object);
        mChatDataPresenter.onFileIsRemoved(object.toString());
    }

    void onServerError(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_SERVER_ERROR" + jsonObject);
        ChatServerError chatServerError;
        if (jsonObject.has(ChatServerError.ACTION_KEY)) {
            String action = jsonObject.optString(ChatServerError.ACTION_KEY);
            String jsonString = jsonObject.toString();
            switch (action) {
                case ChatServerError.CREATE_GROUP_ERROR:
                    chatServerError = mGson.fromJson(jsonString,ChatCreateGroupError.class);
                    break;
                case ChatServerError.CHANGE_GROUP_ERROR:
                    chatServerError = mGson.fromJson(jsonString, ChatChangeGroupError.class);
                    break;
                case ChatServerError.SET_USER_STATUS_ERROR:
                    chatServerError = mGson.fromJson(jsonString, ChatSetUserStatusError.class);
                    break;
                case ChatServerError.ADD_USER_ERROR:
                    chatServerError = mGson.fromJson(jsonString, ChatAddUserError.class);
                    break;
                case ChatServerError.USER_IS_WRITING:
                    chatServerError = mGson.fromJson(jsonString, ChatUserIsWritingError.class);
                    break;
                case ChatServerError.SEND_MESSAGE:
                    chatServerError = mGson.fromJson(jsonString, ChatSendMessageError.class);
                    break;
                case ChatServerError.GET_HISTORY:
                case ChatServerError.GET_HISTORY_SPECIAL:
                    chatServerError = mGson.fromJson(jsonString, ChatGetHistoryError.class);
                    break;
                case ChatServerError.GET_USERS:
                case ChatServerError.GET_USERS_NOT_IN_GROUP:
                case ChatServerError.GET_USERS_ALL_NOT_IN_GROUP:
                case ChatServerError.GET_USERS_ALL_REGISTERED:
                    chatServerError = mGson.fromJson(jsonString, ChatGetUsersError.class);
                    break;
                case ChatServerError.SEND_TO_USER:
                    chatServerError = mGson.fromJson(jsonString, ChatSendToUserError.class);
                    break;
                case ChatServerError.SEND_TO_GROUP:
                    chatServerError = mGson.fromJson(jsonString, ChatSendToGroupError.class);
                    break;
                case ChatServerError.SEND_TO_ALL:
                    chatServerError = mGson.fromJson(jsonString, ChatSendToAllError.class);
                    break;
                case ChatServerError.SET_READED:
                    chatServerError = mGson.fromJson(jsonString, ChatSetReadedError.class);
                    break;
                case ChatServerError.CHECK_UNREAD:
                    chatServerError = mGson.fromJson(jsonString, ChatCheckUnreadError.class);
                    break;
                case ChatServerError.CHANGE_USERS_ROLES:
                    chatServerError = mGson.fromJson(jsonString, ChatChangeUsersRolesError.class);
                    break;
                case ChatServerError.RESTRICT_USERS:
                    chatServerError = mGson.fromJson(jsonString, ChatRestrictUsersError.class);
                    break;
                case ChatServerError.DELETE_MESSAGE:
                    chatServerError = mGson.fromJson(jsonString, ChatDeleteMessageError.class);
                    break;
                case ChatServerError.EDIT_MESSAGE:
                    chatServerError = mGson.fromJson(jsonString, ChatEditMessageError.class);
                    break;
                case ChatServerError.BLOCK_GROUP:
                    chatServerError = mGson.fromJson(jsonString, ChatBlockGroupError.class);
                    break;
                case ChatServerError.UNBLOCK_GROUP:
                    chatServerError = mGson.fromJson(jsonString, ChatUnblockGroupError.class);
                    break;
                case ChatServerError.STORE_FILE:
                    chatServerError = mGson.fromJson(jsonString, ChatStoreFileError.class);
                    break;
                case ChatServerError.SET_REQUEST_STATUS:
                    chatServerError = mGson.fromJson(jsonString, ChatSetRequestStatusError.class);
                    break;
                case ChatServerError.REMOVE_FILE:
                    chatServerError = mGson.fromJson(jsonString, ChatRemoveFileError.class);
                    break;

                default:
                    chatServerError = mGson.fromJson(jsonString, ChatUndefinedError.class);
                    break;
            }
        }
        else {
            chatServerError = mGson.fromJson(jsonObject.toString(), ChatServerUnknownError.class);
        }

        mChatDataPresenter.onError(chatServerError);
    }

    void onAuthTokenError(JSONObject jsonObject) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ON_AUTH_TOKEN_ERROR" + jsonObject);
        mChatDataPresenter.onAuthTokenError(
                mGson.fromJson(jsonObject.toString(),
                ChatAuthTokenError.class));
    }

    //base Socket status callback
    void onEventConnect(Object... object) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "Connect");
        if (object != null && object.length > 0) {
            mChatConnectionPresenter.onConnect(object);
        }

        mChatMessageSendPresenter.updateUnreadMessagesList();
        if (mChatMessageSendPresenter.getUnsendedMessages().size() > 0) {
            mChatMessageSendPresenter.sendAllUnsendedMessages();
        }
    }

    void onEventDisconnect(Object object) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_DISCONNECT " + object);
        mChatConnectionPresenter.onDisconnect(object);
    }

    void onEventReconnect(Object object) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_RECONNECT " + object);
        mChatConnectionPresenter.onReconnect(object);
    }

    void onEventConnectError(Object object) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_CONNECT_ERROR " + object);
        mChatConnectionPresenter.onConnectionError(object);
    }

    void onEventError(Object object) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_ERROR " + object);
        mChatConnectionPresenter.onError(object);
    }

    void onEventConnectTimeout(Object object) {
        mChatLogPresenter.i(ChatWebSocket.TAG, "EVENT_CONNECT_TIMEOUT " + object);
        mChatConnectionPresenter.onEventConnectTimeout(object);
    }
}