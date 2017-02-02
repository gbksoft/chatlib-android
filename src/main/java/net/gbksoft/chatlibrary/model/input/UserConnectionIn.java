package net.gbksoft.chatlibrary.model.input;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Input object from server on USER_IN event
 * contains get methods.
 */

public class UserConnectionIn {
    @SerializedName("user")
    private User userIn;
    private String groupId;

    public User getUserIn() {
        return userIn;
    }

    public String getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "{userIn: {" + userIn + "}, groupId: " + groupId + "}";
    }
}