package net.gbksoft.chatlibrary.model.output;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Output object to server of ChatWebSocket class blockGroup/unblockGroup methods
 */

public class BlockGroup implements IOutputComponent {
    private String groupId;
    @SerializedName("messageText")
    private String reason;

    public BlockGroup(String groupId, String reason) {
        this.groupId = groupId;
        this.reason = reason;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("groupId", groupId);
            object.put("messageText", reason);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}