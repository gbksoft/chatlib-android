package net.gbksoft.chatlibrary.model.input.errors;

/**
 *
 * Input object from server on SERVER_ERROR event onChangeGroup type
 */

public class ChatChangeGroupError extends
        ChatServerError<ChatChangeGroupError.ChangeGroupErrorReason> {

    public static final String THE_SAME_GROUP = "the same group";
    public static final String NO_SUCH_GROUP = "no such group";
    public static final String NO_RIGHTS_TO_JOIN = "no rights to join";
    public static final String YOU_ARE_BANNED = "you are banned";
    public static final String GROUP_IS_BLOCKED = "group is blocked";

    public class ChangeGroupErrorReason {
        private String senderId;
        private String groupId;

        public String getSenderId() {
            return senderId;
        }

        public String getGroupId() {
            return groupId;
        }

        @Override
        public String toString() {
            return "{senderId: \"" + senderId + "\", groupId: \"" + groupId + "\"}";
        }
    }
}