package net.gbksoft.chatlibrary.model.output;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Output object to server of ChatWebSocket class ChatFileRequest class methods
 */

public class StoreFile implements IOutputComponent {
    private String fileName;
    private String contentType;
    private long contentLength;
    private String messageType;
    private String messageTypeId;

    public StoreFile(String fileName, String contentType, long contentLength,
             String messageType, String messageTypeId) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.messageType = messageType;
        this.messageTypeId = messageTypeId;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("fileName", fileName);
            object.put("contentType", contentType);
            object.put("contentLength", contentLength);
            object.put("messageType", messageType);
            object.put("messageTypeId", messageTypeId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }
}