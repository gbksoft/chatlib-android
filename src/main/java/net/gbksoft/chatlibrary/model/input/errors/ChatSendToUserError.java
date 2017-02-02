package net.gbksoft.chatlibrary.model.input.errors;

/**
 *
 * Input object from server on SERVER_ERROR event sendToUser type
 */

public class ChatSendToUserError extends
        ChatServerError<ChatSendToUserError.SendToUserErrorReason> {

    public class SendToUserErrorReason {
        private String recipientId;
        private String messageType;
        private Object messageData;

        public String getRecipientId() {
            return recipientId;
        }

        public String getMessageType() {
            return messageType;
        }

        public Object getMessageData() {
            return messageData;
        }

        @Override
        public String toString() {
            return "{recipientId: \"" + recipientId + "\", messageType: \"" + messageType + "\"," +
                    "messageData: \"" + messageData + "\"}";
        }
    }
}