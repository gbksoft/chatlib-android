package net.gbksoft.chatlibrary.model.output;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Output object to server of ChatWebSocket class ChatRequest class methods
 */

public class Request implements IOutputComponent {
    protected String groupId;
    protected String userId;
    protected String messageText;

    public Request() {
    }

    public Request(String groupId, String messageText) {
        this(groupId, null, messageText);
    }

    public Request(String groupId, String userId, String messageText) {
        this.groupId = groupId;
        this.userId = userId;
        this.messageText = messageText;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("groupId", groupId);
            if (userId != null) {
                object.put("userId", userId);
            }
            object.put("messageText", messageText);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}