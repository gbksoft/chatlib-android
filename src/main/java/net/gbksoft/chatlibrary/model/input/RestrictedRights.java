package net.gbksoft.chatlibrary.model.input;

import net.gbksoft.chatlibrary.utils.StringUtils;

import java.util.ArrayList;

/**
 *
 * Input object from server on RESTRICTED_RIGHTS event
 * contains get methods.
 */

public class RestrictedRights {
    private String groupId;
    private long period;
    private ArrayList<String> userIds;

    public String getGroupId() {
        return groupId;
    }

    public long getPeriod() {
        return period;
    }

    public ArrayList<String> getUserIds() {
        return userIds;
    }

    @Override
    public String toString() {
        return "{groupId: \"" + groupId + "\", period: " + period + "," +
                "userIds: [" + StringUtils.getStringFromArray(userIds) + "]}";
    }
}