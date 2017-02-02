package net.gbksoft.chatlibrary.model.output;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Output object to server of ChatWebSocket class getHistory | getHistorySpecial methods
 */

public class History implements IOutputComponent {
    protected String type;
    protected String typeId;
    protected long from;
    private long byTime;

    public History() {
    }

    public History(String type, String typeId) {
        this(type, typeId, -1, -1);
    }

    public History(String type, String typeId, long from) {
        this(type, typeId, from, -1);
    }

    public History(String type, String typeId, long from, long byTime) {
        this.type = type;
        this.typeId = typeId;
        this.from = from;
        this.byTime = byTime;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("type", type);
            object.put("typeId", typeId);

            if (from != -1) {
                object.put("from", from);
            }

            if (byTime != -1) {
                object.put("byTime", byTime);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}