package net.gbksoft.chatlibrary.model.output;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Output object to server of ChatWebSocket class setRequestStatus method
 * Input object from server on REQUEST_STATUS_IS_CHANGED event
 */

public class RequestStatus implements IOutputComponent {
    public static final String ACCEPTED_STATUS = "accepted";
    public static final String REJECTED_STATUS = "rejected";

    protected String messageId;
    protected String status;

    public RequestStatus() {
    }

    public RequestStatus(String messageId, boolean isAccepted) {
        this.messageId = messageId;
        this.status = isAccepted ? ACCEPTED_STATUS : REJECTED_STATUS;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("messageId", messageId);
            object.put("status", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }
}