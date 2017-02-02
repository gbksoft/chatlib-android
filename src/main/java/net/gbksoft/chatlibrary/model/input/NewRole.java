package net.gbksoft.chatlibrary.model.input;

import net.gbksoft.chatlibrary.utils.StringUtils;

import java.util.ArrayList;

/**
 *
 * Input object from server on NEW_ROLE event
 * contains get methods.
 */

public class NewRole {
    private String groupId;
    private String role;
    private ArrayList<String> userIds;

    public String getGroupId() {
        return groupId;
    }

    public String getRole() {
        return role;
    }

    public ArrayList<String> getUserIds() {
        return userIds;
    }

    @Override
    public String toString() {
        return "{groupId: \"" + groupId + "\", role: \"" + role + "\"," +
                "userIds: [" + StringUtils.getStringFromArray(userIds) + "]}";
    }
}