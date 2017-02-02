package net.gbksoft.chatlibrary.model.input;

/**
 *
 * Input object from server on MESSAGE_ADD event
 * contains get methods.
 */

public class MessageAdd {
    private String messageId;
    private MessageAddPreview preview;

    public String getMessageId() {
        return messageId;
    }

    public MessageAddPreview getPreview() {
        return preview;
    }

    public class MessageAddPreview {
        private String url;
        private String contentType;
        private long contentLength;

        public String getContentType() {
            return contentType;
        }

        public String getUrl() {
            return url;
        }

        public long getContentLength() {
            return contentLength;
        }

        @Override
        public String toString() {
            return "{url: \"" + url + "\", contentType: \"" + contentType + "\"," +
                    "contentLength: " + contentLength + "}";
        }
    }

    @Override
    public String toString() {
        return "{messageId: \"" + messageId + "\", preview: " + preview + "}";
    }
}