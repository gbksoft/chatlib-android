package net.gbksoft.chatlibrary.model.output;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *
 * Output object to server of ChatWebSocket class banUser|banUsers|unbanUser|unbanUsers methods
 */

public class RestrictUser implements IOutputComponent {
    private String groupId;
    private ArrayList<String> userIds;
    private long period;

    public RestrictUser(String groupId, ArrayList<String> userIds, long period) {
        this.groupId = groupId;
        this.userIds = userIds;
        this.period = period;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("groupId", groupId);
            object.put("period", period);

            JSONArray jsonArray = new JSONArray(userIds);
            object.put("userIds", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }
}