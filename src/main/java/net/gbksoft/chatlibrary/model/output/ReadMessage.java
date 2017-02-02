package net.gbksoft.chatlibrary.model.output;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *
 * Output object to server of ChatWebSocket class setReaded|setReadedAll methods
 */

public class ReadMessage implements IOutputComponent {
    private String senderId;
    @SerializedName("messageIds0")
    private ArrayList<String> messageIds;

    public ReadMessage(String senderId) {
        this.senderId = senderId;
    }

    public ReadMessage(String senderId, ArrayList<String> messageIds) {
        this.senderId = senderId;
        this.messageIds = messageIds;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("senderId", senderId);
            if (messageIds != null && messageIds.size() > 0) {
                JSONArray jsonArray = new JSONArray(messageIds);
                object.put("messageIds0", jsonArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}