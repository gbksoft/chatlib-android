package net.gbksoft.chatlibrary.chatdata;

import net.gbksoft.chatlibrary.model.input.*;
import net.gbksoft.chatlibrary.model.input.errors.*;
import net.gbksoft.chatlibrary.model.input.status.*;

/**
 *
 * Class contains interface and adapter, that returns data from server to the dependency project
 */

public class ChatDataListeners {
    public interface IChatDataListener {
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
    }

    public static abstract class ChatDataListenerAdapter implements IChatDataListener {
        @Override
        public void onConnected(UserData userData) {

        }

        @Override
        public void onJoinedTo(JoinedGroup groupName) {

        }

        @Override
        public void onNewGroup(Group group) {

        }

        @Override
        public void onMessageBroadcastReceived(Message inputMessage) {

        }

        @Override
        public void onMessageGroupReceived(Message inputMessage) {

        }

        @Override
        public void onMessageUserReceived(Message inputMessage) {

        }

        @Override
        public void onHistoryData(History history) {

        }

        @Override
        public void onUnreadMessages(UnreadMessages unreadMessages) {

        }

        @Override
        public void onUserOut(UserConnectionOut userConnectionOut) {

        }

        @Override
        public void onUserIn(UserConnectionIn userConnectionIn) {

        }

        @Override
        public void onGroupUsers(GroupUsers data) {

        }

        @Override
        public void onMessagesAreRead(ReadStatus readStatus) {

        }

        @Override
        public void onMessagesIsSent(SendStatus sendStatus) {

        }

        @Override
        public void onUserIsWriting(User user) {

        }

        @Override
        public void onUserStatus(UserStatus userStatus) {

        }

        @Override
        public void onRestrictedRights(RestrictedRights restrictedRights) {

        }

        @Override
        public void onChangeUserRoles(NewRole userRole) {

        }

        @Override
        public void onMessageIsDeleted(MessageIsDeleted message) {

        }

        @Override
        public void onMessageIsEdited(MessageIsEdited message) {

        }

        @Override
        public void onBlockGroup(GroupIsBlocked response) {

        }

        @Override
        public void onUnblockGroup(GroupIsUnblocked response) {

        }

        @Override
        public void onRequestAccepted(RequestAccepted response) {

        }

        @Override
        public void onRequestForBan(UserRequest response) {

        }

        @Override
        public void onRequestForUnban(UserRequest response) {

        }

        @Override
        public void onRequestForBlockGroup(GroupRequest response) {

        }

        @Override
        public void onRequestForUnblockGroup(GroupRequest response) {

        }

        @Override
        public void onRequestForJoin(GroupRequest response) {

        }

        @Override
        public void onHistorySpecial(HistorySpecial response) {

        }

        @Override
        public void onFileIsStored(FileIsStored response) {

        }

        @Override
        public void onRequestStatusIsChanged(RequestStatus response) {

        }

        @Override
        public void onMessageAdd(MessageAdd response) {

        }

        @Override
        public void onFileIsRemoved(String response) {

        }

        @Override
        public void onAuthTokenError(ChatAuthTokenError error) {

        }

        @Override
        public abstract void onError(ChatServerError error);
    }
}