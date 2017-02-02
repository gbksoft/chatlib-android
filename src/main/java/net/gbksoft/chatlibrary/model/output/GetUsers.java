package net.gbksoft.chatlibrary.model.output;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Output object to server of ChatWebSocket class getNotInGroupUsers method
 */

public class GetUsers implements IOutputComponent {

    public static final String ALL_REGISTERED = "allRegistered";
    public static final String IN_GROUP = "inGroup";
    public static final String NOT_IN_GROUP = "notInGroup";
    public static final String ALL_NOT_IN_GROUP = "allNotInGroup";

    private String groupId;
    private String typeRequest;

    public GetUsers() {
    }

    public GetUsers(String groupId, String typeRequest) {
        this.groupId = groupId;
        this.typeRequest = typeRequest;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            if (groupId == null) {
                object.put(ALL_REGISTERED, 1);
            }
            else {
                switch (typeRequest) {
                    case IN_GROUP:
                        object.put(IN_GROUP, groupId);
                        break;
                    case NOT_IN_GROUP:
                        object.put(NOT_IN_GROUP, groupId);
                        break;
                    case ALL_NOT_IN_GROUP:
                        object.put(ALL_NOT_IN_GROUP, groupId);
                        break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }
}