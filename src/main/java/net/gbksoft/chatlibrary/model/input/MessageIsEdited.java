package net.gbksoft.chatlibrary.model.input;

/**
 *
 * Input object from server on MESSAGE_IS_EDITED event
 * contains get methods.
 */

public class MessageIsEdited {
    private String messageId;
    private String messageText;
    private long timestampEditing;

    public String getMessageId() {
        return messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public long getTimestampEditing() {
        return timestampEditing;
    }

    @Override
    public String toString() {
        return "{messageId: \"" + messageId + "\", messageText: \"" + messageText + "\"," +
                "timestampEditing: " + timestampEditing + "}";
    }
}