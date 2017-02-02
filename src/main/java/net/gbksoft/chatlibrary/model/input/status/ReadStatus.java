package net.gbksoft.chatlibrary.model.input.status;

import com.google.gson.annotations.SerializedName;

import net.gbksoft.chatlibrary.utils.StringUtils;

import java.util.ArrayList;

/**
 *
 * Input object from server on MESSAGES_ARE_READ event
 * contains get methods.
 */

public class ReadStatus {
    @SerializedName("companionId")
    private String recipientId;

    @SerializedName("messageIds0")
    private ArrayList<String> messageIds;

    public String getRecipientId() {
        return recipientId;
    }

    public ArrayList<String> getMessageIds() {
        return messageIds;
    }

    @Override
    public String toString() {
        return "{recipientId: \"" + recipientId + "\"," +
                "messageIds: [" + StringUtils.getStringFromArray(messageIds) + "]}";
    }
}