package net.gbksoft.chatlibrary.model.input;

import com.google.gson.annotations.SerializedName;

/**
 *
* Input object from server on REQUEST_ACCEPTED event
 * contains get methods.
 */

public class RequestAccepted extends net.gbksoft.chatlibrary.model.output.Request {
    @SerializedName("typeSpecial")
    private String type;
    private String senderId;

    public String getType() {
        return type;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getUserId() {
        return userId;
    }

    public String getReason() {
        return messageText;
    }

    @Override
    public String toString() {
        return "{type: \"" + type + "\", groupId: \"" + groupId + "\", userId: \"" + userId +
                "\"messageText: \"" + messageText + " \"senderId: \"" + senderId + "\"}";
    }
}