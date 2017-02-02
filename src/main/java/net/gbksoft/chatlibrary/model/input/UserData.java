package net.gbksoft.chatlibrary.model.input;

import com.google.gson.annotations.SerializedName;

import net.gbksoft.chatlibrary.utils.StringUtils;

import java.util.ArrayList;

/**
 *
 * Input object from server on CONNECTED event
 * contains get methods.
 */

public class UserData {
    private String userId;
    private String socketId;
    private ArrayList<Group> groups;
    @SerializedName("APIVersion")
    private String apiVersion;
    private String serverId;

    public String getUserId() {
        return userId;
    }

    public String getSocketId() {
        return socketId;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getServerId() {
        return serverId;
    }

    @Override
    public String toString() {
        return "{\"apiVersion\":" + "\"" + apiVersion + "\",\n" +
                "\"serverId\":" + "\"" + serverId + "\",\n" +
                "\"userId\":" + "\"" + userId + "\",\n" +
                "\"socketId\":" + "\"" + socketId + "\",\n" +
                "\"groups\":" + StringUtils.getStringFromArray(groups) + "}";
    }
}