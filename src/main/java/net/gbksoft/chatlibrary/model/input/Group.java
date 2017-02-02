package net.gbksoft.chatlibrary.model.input;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import net.gbksoft.chatlibrary.model.Numerator;
import net.gbksoft.chatlibrary.utils.StringUtils;

import java.util.ArrayList;

/**
 *
 * Input object from server on NEW_GROUP event
 * contains get methods.
 */

public class Group extends net.gbksoft.chatlibrary.model.output.Group {
    private String id;
    private long timestamp;
    private int needBadgesNum;
    @SerializedName("usersCnt")
    private int userCount;
    private String owner;
    protected ArrayList<String> moderatorIds;
    protected ArrayList<String> bannedIds;
    private Numerator mBadgeNumerator;
    private int blocked;
    @SerializedName("messageText")
    private String blockedMessageText;

    public Group() {
        mBadgeNumerator = new Numerator();
    }

    public String getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public ArrayList<String> getModeratorIds() {
        return moderatorIds;
    }

    public void addModeratorId(String id) {
        moderatorIds.add(id);
    }

    public void removeModeratorId(int id) {
        moderatorIds.remove(id);
    }

    public ArrayList<String> getBannedIds() {
        return bannedIds;
    }

    public ArrayList<String> getUserIds() {
        return userIds;
    }

    public boolean isNeedBadges() {
        return needBadges;
    }

    public int getUserCount() {
        return userCount;
    }

    public String getType() {
        return type;
    }

    public boolean isClosed() {
        return !TextUtils.isEmpty(type) && type.toLowerCase().equals("closed");
    }

    public int getNeedBadgesNum() {
        return needBadgesNum;
    }

    public int getBadge() {
        return mBadgeNumerator.getNumber();
    }

    public int setBadge(int badge) {
        return mBadgeNumerator.setNumber(badge);
    }

    public int incrementBadge() {
        return mBadgeNumerator.increment();
    }

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public String getBlockedMessageText() {
        return blockedMessageText;
    }

    public void setBlockedMessageText(String blockedMessageText) {
        this.blockedMessageText = blockedMessageText;
    }

    @Override
    public String toString() {
        return "id:\"" + id + "\", name:\"" + name + "\", owner:\"" + owner + "\", " +
                "moderatorIds:[" + StringUtils.getStringFromArray(moderatorIds, false) + "], " +
                "bannedIds:[" + StringUtils.getStringFromArray(bannedIds, false) + "], " +
                "userIds:[" + StringUtils.getStringFromArray(userIds, false) + "], " +
                "needBadges:" + needBadges + ", timestamp:" + timestamp + "," +
                "userCount:" + userCount + ", badgeCount:" + mBadgeNumerator.getNumber() + ", " +
                "blocked:" + blocked + ", blockedMessageText:" + blockedMessageText + ", " +
                "\"needBadgesNum\":" + needBadgesNum;
    }
}