package net.gbksoft.chatlibrary.model.input.errors;

import com.google.gson.annotations.SerializedName;

import net.gbksoft.chatlibrary.model.output.Message;

/**
 *
 * Input object from server on SERVER_ERROR event onSendMessage type
 */

public class ChatSendMessageError extends
        ChatServerError<ChatSendMessageError.UserIsWritingErrorReason> {

    public class UserIsWritingErrorReason {
        @SerializedName("sender.id")
        private String senderId;
        private String recipientId;
        private Message message;

        public String getSenderId() {
            return senderId;
        }

        public String getRecipientId() {
            return recipientId;
        }

        public Message getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "{senderId: \"" + senderId + "\", recipientId: \"" + recipientId + "\"," +
                    "message: {" + recipientId + "}}";
        }
    }
}