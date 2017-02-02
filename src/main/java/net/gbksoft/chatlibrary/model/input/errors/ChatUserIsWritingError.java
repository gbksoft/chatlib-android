package net.gbksoft.chatlibrary.model.input.errors;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Input object from server on SERVER_ERROR event onUserIsWriting type
 */

public class ChatUserIsWritingError extends
        ChatServerError<ChatUserIsWritingError.UserIsWritingErrorReason> {

    public class UserIsWritingErrorReason {
        @SerializedName("sender.id")
        private String senderId;
        private String recipientId;

        public String getSenderId() {
            return senderId;
        }

        public String getRecipientId() {
            return recipientId;
        }

        @Override
        public String toString() {
            return "{senderId: \"" + senderId + "\", recipientId: \"" + recipientId + "\"}";
        }
    }
}