package net.gbksoft.chatlibrary.model.input.errors;

/**
 *
 * Input object from server on SERVER_ERROR event sendToAll type
 */

public class ChatSendToAllError extends
        ChatServerError<ChatSendToAllError.SendToAllErrorReason> {

    public class SendToAllErrorReason {
        @Override
        public String toString() {
            return "";
        }
    }
}