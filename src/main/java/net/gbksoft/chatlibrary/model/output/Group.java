package net.gbksoft.chatlibrary.model.output;

import android.text.TextUtils;

import net.gbksoft.chatlibrary.utils.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *
 * Output object to server of ChatWebSocket class changeGroup method
 */

public class Group implements IOutputComponent {
    public static final String GROUP_OPEN_TYPE = "open";
    public static final String GROUP_CLOSED_TYPE = "closed";
    public static final String GROUP_SECRET_TYPE = "secret";

    protected String name;
    protected ArrayList<String> userIds;
    protected boolean needBadges;
    protected String type;

    public Group() {}

    public Group(String name, ArrayList<String> userIds, boolean needBadges, String type) {
        this.name = name;
        this.userIds = userIds;
        this.needBadges = needBadges;
        this.type = type;
    }

    /**
     * Created JsonObject of this object.
     * if userIds exists - than group is closed
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("name", name);
            if (userIds != null && userIds.size() != 0) {
                JSONArray jsonArray = new JSONArray(userIds);
                object.put("userIds", jsonArray);
            }
            if (TextUtils.isEmpty(type)) {
                object.put("type", GROUP_OPEN_TYPE);
            }
            else {
                object.put("type", type);
            }
            object.put("needBadges", needBadges);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public String toString() {
        return "{name:\"" + name + "\" userIds:" + StringUtils.getStringFromArray(userIds) + "\n" +
                "needBadges:" + needBadges + "}";
    }
}