package net.gbksoft.chatlibrary.model.input;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Input object from server on
 *      onRequestForBlockGroup |
 *      onRequestForUnblockGroup |
 *      onRequestForJoin
 *   event
 * contains get methods.
 */

public class GroupRequest {
    protected String messageId;
    protected String groupId;
    protected String senderName;
    protected String requestedGroupId;
    protected String messageText;
    protected String senderId;
    protected String structuredData;

    public String getGroupId() {
        return groupId;
    }

    public String getRequestedGroupId() {
        if(requestedGroupId == null || requestedGroupId.equals("null")) {
            try {
                requestedGroupId = new JSONObject(structuredData).optString("requestedGroupId", "-1");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return requestedGroupId;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getMessageId() {
        return messageId;
    }

    @Override
    public String toString() {
        return "{\"messageId\":" + "\"" + messageId + "\", \"groupId\":" + "\"" + groupId + "\", " +
                "\"requestedGroupId\":" + "\"" + requestedGroupId + "\", \"messageText\":" + "\"" +
                messageText + "\", \"senderName\":" + "\"" + senderName + "\", \"senderId\":" +
                "\"" + senderId + "\"}";
    }

    public static GroupRequest createFromHistoryEvent(Message msg) {
        GroupRequest gr = new GroupRequest();
        gr.messageId = msg.getMessageId();
        gr.groupId = msg.getGroupId();
        gr.senderName = msg.getSenderName();
        String sds = msg.getStructuredDataString();
        if(sds.length() > 0) {
            try {
                gr.requestedGroupId = new JSONObject(sds).optString("requestedGroupId", "-3");
            } catch (JSONException e) {
                e.printStackTrace();
                gr.requestedGroupId = "-2";
            }
        }
        else {
            gr.requestedGroupId = "-4";
        }
        gr.messageText = msg.getText();
        gr.senderId = msg.getSenderId();
        return gr;
    }
}