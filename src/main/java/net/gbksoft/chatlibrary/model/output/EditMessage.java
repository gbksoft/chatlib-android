package net.gbksoft.chatlibrary.model.output;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Output object to server of ChatWebSocket class editMessage method
 */

public class EditMessage implements IOutputComponent {
    private String messageId;
    private String messageText;

    public EditMessage(String messageId, String messageText) {
        this.messageId = messageId;
        this.messageText = messageText;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("messageId", messageId);
            object.put("messageText", messageText);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}