package net.gbksoft.chatlibrary.model.input;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import net.gbksoft.chatlibrary.utils.StringUtils;

import java.util.ArrayList;

/**
 *
 * Input object from server on onGroupUsers event
 * contains get methods.
 */

public class GroupUsers {
    @SerializedName("inGroup")
    private String groupIdIn;
    @SerializedName("notInGroup")
    private String groupIdNotIn;
    @SerializedName("allNotInGroup")
    private String groupIdAllNotIn;
    @SerializedName("allRegistered")
    private int allRegistered;
    private ArrayList<User> users;

    public String getGroupIdIn() {
        return groupIdIn;
    }

    public String getGroupIdNotIn() {
        return groupIdNotIn;
    }

    public String getGroupIdAllNotIn() {
        return groupIdAllNotIn;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public int getAllRegistered() {
        return allRegistered;
    }

    public boolean isInGroupUsers() {
        return !TextUtils.isEmpty(groupIdIn);
    }

    public boolean isNotInGroupUsers() {
        return !TextUtils.isEmpty(groupIdNotIn);
    }

    public boolean isAllNotInGroupUsers() {
        return !TextUtils.isEmpty(groupIdAllNotIn);
    }

    @Override
    public String toString() {
        return "users: [" + StringUtils.getStringFromArray(users) + "],\n groupIdIn: " +
                groupIdIn + ", groupIdNotIn: " + groupIdNotIn + ", groupIdAllNotIn: " +
                groupIdAllNotIn + ", allRegistered: " + allRegistered;
    }
}