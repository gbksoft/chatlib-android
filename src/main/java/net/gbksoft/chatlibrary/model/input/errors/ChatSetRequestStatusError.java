package net.gbksoft.chatlibrary.model.input.errors;

/**
 *
 * Input object from server on SERVER_ERROR event onSetRequestStatus type
 */

public class ChatSetRequestStatusError extends
        ChatServerError<ChatSetRequestStatusError.ChatSetRequestStatusErrorReason> {

    public class ChatSetRequestStatusErrorReason {
        private String messageId;

        public String getMessageId() {
            return messageId;
        }

        @Override
        public String toString() {
            return "{messageId: \"" + messageId + "\"}";
        }
    }
}