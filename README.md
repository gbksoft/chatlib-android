# chatlib-android

## Android library project which allows to create chat applications with rich support of such features as:

1. Group messaging
2. Private messaging
3. Public, private or closed room creations
4. Moderation of messages, users and rooms
5. Chat administration options for managing request room blocks/unblocks and user block/unblock
6. Sending smiles, audio-video-images attachments
7. Sending location data

## Sample usage

init connection

    ChatWebSocket mChatWebSocket = new ChatWebSocketBuilder()
            .setChatFrontScheme("http")
            .setChatFrontPath(BuildConfig.CHAT_SERVER)
            .setPort(BuildConfig.CHAT_PORT)
            .setChatSocketNamespace("/front_space")
            .build();


add listener, and implement needed callbacks

    ChatDataListeners.ChatDataListenerAdapter socketListener = new ChatDataListeners.ChatDataListenerAdapter() {

        //implement needed methods here, like onConnect, onMessageReceived etc

        @Override
        public void onError(ChatServerError error) {
            Log.e("Error", error.toString());
        }
    };
    mChatWebSocket.addChatDataListener(socketListener);


### list of methods
    void onConnected(UserData userData);
    void onJoinedTo(JoinedGroup group);
    void onNewGroup(Group group);
    void onMessageUserReceived(Message inputMessage);
    void onMessageGroupReceived(Message inputMessage);
    void onMessageBroadcastReceived(Message inputMessage);
    void onHistoryData(History history);
    void onUnreadMessages(UnreadMessages unreadMessages);
    void onUserIn(UserConnectionIn userConnectionIn);
    void onUserOut(UserConnectionOut userConnectionOut);
    void onGroupUsers(GroupUsers data);
    void onUserIsWriting(User user);
    void onMessagesIsSent(SendStatus sendStatus);
    void onMessagesAreRead(ReadStatus readStatus);
    void onUserStatus(UserStatus userStatus);
    void onChangeUserRoles(NewRole userRole);
    void onRestrictedRights(RestrictedRights restrictedRights);
    void onMessageIsDeleted(MessageIsDeleted message);
    void onMessageIsEdited(MessageIsEdited message);
    void onBlockGroup(GroupIsBlocked response);
    void onUnblockGroup(GroupIsUnblocked response);
    void onRequestAccepted(RequestAccepted response);
    void onRequestForBan(UserRequest response);
    void onRequestForUnban(UserRequest response);
    void onRequestForBlockGroup(GroupRequest response);
    void onRequestForUnblockGroup(GroupRequest response);
    void onRequestForJoin(GroupRequest response);
    void onHistorySpecial(HistorySpecial response);
    void onFileIsStored(FileIsStored response);
    void onRequestStatusIsChanged(RequestStatus response);
    void onMessageAdd(MessageAdd response);
    void onFileIsRemoved(String response);
    void onError(ChatServerError error);
    void onAuthTokenError(ChatAuthTokenError error);