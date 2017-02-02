package net.gbksoft.chatlibrary.model.input.errors;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Input object from server on SERVER_ERROR event sendToGroup type
 */

public class ChatSendToGroupError extends
        ChatServerError<ChatSendToGroupError.SendToGroupErrorReason> {

    public class SendToGroupErrorReason {
        @SerializedName("sender.id")
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