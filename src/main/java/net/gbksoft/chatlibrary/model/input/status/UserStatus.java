package net.gbksoft.chatlibrary.model.input.status;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Input object from server on USER_STATUS event
 * contains get methods.
 */

public class UserStatus {
    @SerializedName("id")
    private String userId;
    private String status;

    public String getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "{id: \"" + userId + "\", status: \"" + status + "\"}";
    }
}