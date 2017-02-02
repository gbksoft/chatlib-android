package net.gbksoft.chatlibrary.model.input;

/**
 *
 * Output object to server of ChatWebSocket class setRequestStatus method
 * Input object from server on REQUEST_STATUS_IS_CHANGED event
 */

public class RequestStatus extends net.gbksoft.chatlibrary.model.output.RequestStatus {

    public String getMessageId() {
        return messageId;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "{id: \"" + messageId + "\", status: " + status + "}";
    }
}