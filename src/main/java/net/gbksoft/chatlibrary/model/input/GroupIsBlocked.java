package net.gbksoft.chatlibrary.model.input;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Input object from server on GROUP_IS_BLOCKED event
 * contains get methods.
 */

public class GroupIsBlocked {
    protected String groupId;
    @SerializedName("messageText")
    protected String reason;

    public String getGroupId() {
        return groupId;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "{groupId: \"" + groupId + "\", reason: \"" + reason + "\"}";
    }
}