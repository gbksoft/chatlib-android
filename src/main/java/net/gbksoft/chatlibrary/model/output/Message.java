package net.gbksoft.chatlibrary.model.output;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import net.gbksoft.chatlibrary.model.MessageType;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Output object to server of ChatWebSocket class sendMessage method
 */
public class Message extends SugarRecord implements IOutputComponent {
    @Ignore
    private static int mFrontId = 1;
    @Ignore
    private static Gson mGson = new Gson();

    protected int frontId;
    @SerializedName("messageText")
    protected String text;
    protected String type;
    protected String groupId;
    protected String recipientId;
    protected long timestamp;
    @Ignore
    @SerializedName("structuredData_")
    protected StructuredData structuredData;
    @SerializedName("structuredData")
    protected String structuredDataString;

    public Message() {}

    public Message(String text, String type) {
        this(text, type, "");
    }

    public Message(String text, String type, String id) {
        this(text, type, id, null);
    }

    public Message(String text, String type, String id, StructuredData structuredData) {
        this.text = text;
        this.type = type;

        if (type.equals(MessageType.GROUP_TYPE)) {
            groupId = id;
        }
        else if (type.equals(MessageType.USER_TYPE)) {
            recipientId = id;
        }

        frontId = mFrontId++;

        this.structuredData = structuredData;
        updateStructuredDataString(structuredData);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getFrontId() {
        return frontId;
    }

    public void setFrontId(int frontId) {
        this.frontId = frontId;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setStructuredData(StructuredData structuredData) {
        this.structuredData = structuredData;
        updateStructuredDataString(structuredData);
    }

    public StructuredData getStructuredData() {
        if (structuredData == null && !(structuredDataString == null || structuredDataString.length() == 0)) {
            structuredData = mGson.fromJson(structuredDataString, StructuredData.class);
        }
        return structuredData;
    }

    private void updateStructuredDataString(StructuredData structuredData) {
        if (structuredData != null) {
            structuredDataString = structuredData.toJSONObject().toString().replace("\\", "");
        } else {
            structuredDataString = "";
        }
    }

    public String getStructuredDataString() {
        return structuredDataString;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{text:\"").append(text).append("\",")
               .append("type:\"").append(type).append("\"");

        if (!TextUtils.isEmpty(groupId)) {
            builder.append(", groupId:\"").append(groupId).append("\"}");
        }
        else if (!TextUtils.isEmpty(recipientId)) {
            builder.append(", recipientId:\"").append(recipientId).append("\"");
        }

        if (structuredData != null) {
            builder.append(", structuredData {").append(structuredDataString).append("}");
        }

        builder.append("}");
        return builder.toString();
    }

    /**
     * Created JsonObject of this object.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("frontId", frontId);
            object.put("messageText", text);
            object.put("type", type);
            if (!TextUtils.isEmpty(groupId)) {
                object.put("groupId", groupId);
            }
            else if (!TextUtils.isEmpty(recipientId)) {
                object.put("recipientId", recipientId);
            }

            if (timestamp != 0) {
                object.put("timestampFirstSending", timestamp);
            }

            if (TextUtils.isEmpty(structuredDataString)) {
                structuredDataString = new JSONObject().toString();
            }

            object.put("structuredData", structuredDataString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static class StructuredData implements IOutputComponent {
        private String url;
        private String urlPreview;
        private String contentType;
        private String location;
        private String contacts;
        @SerializedName("authNeeded")
        private boolean isAuthNeeded;
        @SerializedName("publicAccess")
        private boolean isPublicAccess;

        public StructuredData(String url, String contentType, boolean isPublicAccess) {
            this (url, null, contentType, false, isPublicAccess);
        }

        public StructuredData(String url, String contentType, boolean isAuthNeeded,
                boolean isPublicAccess) {

            this (url, null, contentType, isAuthNeeded, isPublicAccess);
        }

        public StructuredData(String url, String urlPreview, String contentType,
                boolean isAuthNeeded, boolean isPublicAccess) {

            this.url = url;
            this.urlPreview = urlPreview;
            this.contentType = contentType;
            this.isAuthNeeded = isAuthNeeded;
            this.isPublicAccess = isPublicAccess;
        }

        public String getUrl() {
            return url;
        }

        public String getUrlPreview() {
            return urlPreview;
        }

        public String getContentType() {
            return contentType;
        }

        public boolean isAuthNeeded() {
            return isAuthNeeded;
        }

        public boolean isPublicAccess() {
            return isPublicAccess;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String value) {
            location = value;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        @Override
        public JSONObject toJSONObject() {
            JSONObject object = new JSONObject();
            try {
                object.put("url", url);
                object.put("contentType", contentType);
                if(location != null) {
                    object.put("location", location);
                }

                if(contacts != null) {
                    object.put("contacts", contacts);
                }

                if (!TextUtils.isEmpty(urlPreview)) {
                    object.put("urlPreview", urlPreview);
                }

                object.put("isAuthNeeded", isAuthNeeded);
                object.put("publicAccess", isPublicAccess);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return object;
        }

        @Override
        public String toString() {
            return "{url:\"" + url + "\", urlPreview:\"" + urlPreview + "\"" +
                    "contentType:\"" + contentType + "\" location:\"" + location + "\"" +
                    "contacts:\"" + contacts + "\" isAuthNeeded:\"" + isAuthNeeded + "\"" +
                    "publicAccess:" + isPublicAccess + "}";
        }
    }
}