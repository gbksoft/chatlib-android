package net.gbksoft.chatlibrary.model.output;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *
 * Output object to server of ChatWebSocket class addUserToClosedGroup method
 */

public class AddUser implements IOutputComponent {
    private String groupId;
    private ArrayList<String> userIds;

    public AddUser(String groupId, ArrayList<String> userIds) {
        this.groupId = groupId;
        this.userIds = userIds;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("groupId", groupId);
            if (userIds != null && userIds.size() > 0) {
                JSONArray jsonArray = new JSONArray(userIds);
                object.put("userIds", jsonArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}