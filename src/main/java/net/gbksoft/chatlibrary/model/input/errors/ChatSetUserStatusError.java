package net.gbksoft.chatlibrary.model.input.errors;

/**
 *
 * Input object from server on SERVER_ERROR event onSetUserStatus type
 */

public class ChatSetUserStatusError extends
        ChatServerError<ChatSetUserStatusError.SetUserStatusErrorReason> {

    public class SetUserStatusErrorReason {
        private String status;

        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "{status: \"" + status + "\"}";
        }
    }
}