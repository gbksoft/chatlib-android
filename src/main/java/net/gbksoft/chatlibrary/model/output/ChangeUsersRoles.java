package net.gbksoft.chatlibrary.model.output;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *
 * Output object to server of ChatWebSocket class changeUserRole|changeUserRoles methods
 */

public class ChangeUsersRoles implements IOutputComponent {
    private String groupId;
    private ArrayList<String> userIds;
    private String role;

    public ChangeUsersRoles(String groupId, ArrayList<String> userIds, String role) {
        this.groupId = groupId;
        this.userIds = userIds;
        this.role = role;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("groupId", groupId);
            object.put("role", role);

            JSONArray jsonArray = new JSONArray(userIds);
            object.put("userIds", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }
}