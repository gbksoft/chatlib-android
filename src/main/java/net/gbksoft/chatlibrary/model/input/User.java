package net.gbksoft.chatlibrary.model.input;

import net.gbksoft.chatlibrary.model.Numerator;

/**
 *
 * Input object from server on USER_IN and USER_OUT events,
 * part of the UserConnectionIn, UserConnectionOut classes
 * contains get methods.
 */


public class User {
    private String id;
    private String nickname;
    private String picture;
    private String status;
    private String moderateGroup;
    private String name;
    private Numerator mBadgeNumerator;

    public User() {
        mBadgeNumerator = new Numerator();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPicture() {
        return picture;
    }

    public String getStatus() {
        return status;
    }

    public String getModerateGroup() {
        return moderateGroup;
    }

    public void setModerateGroup(String moderateGroup) {
        this.moderateGroup = moderateGroup;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "id: \"" + id + "\", nickname: \"" + nickname + "\", picture: \"" + picture + "\"," +
                "status: \"" + status + "\", moderateGroup: \"" + moderateGroup + "\"," +
                "name: \"" + name + "\", badgeCount: " + mBadgeNumerator.getNumber();
    }
}