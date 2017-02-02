package net.gbksoft.chatlibrary.model.input;

import com.google.gson.annotations.SerializedName;

import net.gbksoft.chatlibrary.model.ChatMessageStatus;

/**
 *
 * Input object from server on HISTORY, MESSAGE_USER, MESSAGE_GROUP and MESSAGE_BROADCAST events
 * contains get methods.
 */

public class Message extends net.gbksoft.chatlibrary.model.output.Message {
    @SerializedName("messageId")
    private String id;
    private long timestampFirstSending;
    private long timestampEditing;
    private String status;
    private String senderId;
    private String senderName;
    private String typeSpecial;
    private String senderPicture;

    public Message(net.gbksoft.chatlibrary.model.output.Message outputMessage, String senderId,
            String senderName, String senderPicture) {
        this.frontId = outputMessage.getFrontId();
        this.type = outputMessage.getType();
        this.text = outputMessage.getText();
        this.groupId = outputMessage.getGroupId();
        this.recipientId = outputMessage.getRecipientId();
        this.timestamp = outputMessage.getTimestamp();
        this.structuredData = outputMessage.getStructuredData();
        this.structuredDataString = outputMessage.getStructuredDataString();

        this.timestampFirstSending = timestamp;
        this.status = ChatMessageStatus.SENDED_STATUS;
        this.senderId = senderId;
        this.senderName = senderName;
        this.senderPicture = senderPicture;
    }

    public String getMessageId() {
        return id;
    }

    public long getTimestampFirstSending() {
        return timestampFirstSending;
    }

    public long getTimestampEditing() {
        return timestampEditing;
    }

    public void setTimestampEditing(long timestampEditing) {
        this.timestampEditing = timestampEditing;
    }

    public String getStatus() {
        return status;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSenderPicture() {
        return senderPicture;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecialType() {
        return typeSpecial;
    }

    @Override
    public String toString() {
        return "\"message :{id: \"" + id + "\", frontId: \"" + frontId + "\", text: \"" + text +
                "\", timestamp: \"" + timestamp + "\", " +
                "timestampFirstSending: \"" + timestampFirstSending + "\", " +
                "timestampEditing: \"" + timestampEditing + "\", status: \"" + status +
                "\", type: \"" + type + "\", typeSpecial: \"" + typeSpecial + "\", " +
                "groupId: \"" + groupId + "\", recipientId: \"" + recipientId + "\", " +
                "senderId: \"" + senderId + "\", senderName: \"" + senderName + "\", " +
                "senderPicture: \"" + senderPicture + "\", structuredData: " + getStructuredData() +
                ", structuredDataString: \"" + getStructuredDataString() + "\"}";
    }
}