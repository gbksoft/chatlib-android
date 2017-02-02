package net.gbksoft.chatlibrary.model.input;

/**
 *
 * Input object from server on MESSAGE_IS_DELETED event
 * contains getMessageId method.
 */

public class MessageIsDeleted {
    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    @Override
    public String toString() {
        return messageId;
    }
}