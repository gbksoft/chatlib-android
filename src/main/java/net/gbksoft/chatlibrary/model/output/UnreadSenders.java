package net.gbksoft.chatlibrary.model.output;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *
 * Output object to server of ChatWebSocket class getUnread method
 */

public class UnreadSenders implements IOutputComponent {
    private ArrayList<String> fromSenders;

    public UnreadSenders(ArrayList<String> senders) {
        fromSenders = senders;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            if (fromSenders != null && fromSenders.size() != 0) {
                JSONArray jsonArray = new JSONArray(fromSenders);
                object.put("fromSenders", jsonArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }
}