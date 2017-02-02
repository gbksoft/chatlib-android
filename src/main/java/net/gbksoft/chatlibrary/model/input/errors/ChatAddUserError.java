package net.gbksoft.chatlibrary.model.input.errors;

/**
 *
 * Input object from server on SERVER_ERROR event onAddUser type
 */

public class ChatAddUserError extends ChatServerError<ChatAddUserError.AddUserErrorReason> {

    public class AddUserErrorReason {
        private String userId;
        private String senderId;
        private String groupId;

        public String getUserId() {
            return userId;
        }

        public String getSenderId() {
            return senderId;
        }

        public String getGroupId() {
            return groupId;
        }

        @Override
        public String toString() {
            return "{userId: \"" + userId + "\", senderId: \"" + senderId + "\"," +
                    "groupId: \"" + groupId + "\"}";
        }
    }
}