package net.gbksoft.chatlibrary.model.input;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Input object from server on REQUEST_FOR_BAN | REQUEST_FOR_UNBAN events
 * contains getUserId method.
 */

public class UserRequest extends GroupRequest {
    private String userId;

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "{\"id\":" + "\"" + messageId + "\",\"groupId\":" + "\"" + groupId + "\"," +
                "\"messageText\":" + "\"" + messageText + "\", \"senderId\":" + "\"" + senderId +
                "\", \"userId\":" + "\"" + userId + "\"}";
    }

    public static UserRequest createFromHistoryEvent(Message msg) {
        UserRequest ur = new UserRequest();
        ur.userId = msg.getSenderId();
        ur.messageId = msg.getMessageId();
        ur.groupId = msg.getGroupId();
        ur.senderName = msg.getSenderName();
        try {
            ur.requestedGroupId = new JSONObject(msg.getStructuredDataString()).optString("requestedGroupId", "-3");
        } catch (JSONException e) {
            e.printStackTrace();
            ur.requestedGroupId = "-2";
        }
        ur.messageText = msg.getText();
        ur.senderId = msg.getSenderId();
        return ur;
    }
}