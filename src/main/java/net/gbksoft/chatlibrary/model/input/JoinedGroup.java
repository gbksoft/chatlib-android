package net.gbksoft.chatlibrary.model.input;

import net.gbksoft.chatlibrary.utils.StringUtils;

import java.util.ArrayList;

/**
 *
 * Input object from server on JOINED_TO event
 * contains get methods.
 */

public class JoinedGroup {
    private String groupId;
    private String groupName;
    private String groupType;
    private String groupOwner;
    private String serverId;
    private String role;
    private ArrayList<User> usersHere;
    private ArrayList<User> usersInGroup;

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupType() {
        return groupType;
    }

    public String getGroupOwner() {
        return groupOwner;
    }

    public String getServerId() {
        return serverId;
    }

    public String getRole() {
        return role;
    }

    public ArrayList<User> getUsersHere() {
        return usersHere;
    }

    public ArrayList<User> getUsersInGroup() {
        return usersInGroup;
    }

    @Override
    public String toString() {
        return "{groupId: \"" + groupId + "\", groupOwner: \"" + groupOwner + "\"," +
                "groupName: \"" + groupName + "\", groupType: \"" + groupType + "\"," +
                "serverId: \"" + serverId + "\", role: \"" + role + "\"," +
                "\nusersHere: [" + StringUtils.getStringFromArray(usersHere) + "]," +
                "\nusersInGroup: [" + StringUtils.getStringFromArray(usersInGroup) + "]}";
    }
}