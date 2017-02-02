package net.gbksoft.chatlibrary.model.input.status;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Input object from server on MESSAGES_IS_SENT event
 * contains get methods.
 */

public class SendStatus {
    @SerializedName("companionId")
    private String recipientId;

    @SerializedName("messageId0")
    private String messageId;

    public String getRecipientId() {
        return recipientId;
    }

    public String getMessageId() {
        return messageId;
    }

    @Override
    public String toString() {
        return "{recipientId: \"" + recipientId + "\", messageId: \"" + messageId + "\"}";
    }
}