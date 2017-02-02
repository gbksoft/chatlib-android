package net.gbksoft.chatlibrary.model.input;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Input object from server on USER_OUT event
 * contains get methods.
 */

public class UserConnectionOut {
    @SerializedName("user")
    private User userOut;
    private String groupId;

    public User getUserOut() {
        return userOut;
    }

    public String getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "{userOut: {" + userOut + "}, groupId: " + groupId + "}";
    }
}