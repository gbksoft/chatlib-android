package net.gbksoft.chatlibrary.model.input;

/**
 *
 * Inner input object of FileIsStored class from server on FILE_IS_STORED event
 * contains getMessageId method.
 */

public class Preview {
    protected String url;
    protected String contentType;
    protected long contentLength;
    protected boolean publicAccess;

    public String getUrl() {
        return url;
    }

    public String getContentType() {
        return contentType;
    }

    public long getContentLength() {
        return contentLength;
    }

    public boolean isPublicAccess() {
        return publicAccess;
    }

    @Override
    public String toString() {
        return "{url: \"" + url + "\", contentType: \"" + contentType + "\", " +
                "contentLength: " + contentLength + ", publicAccess: \"" + publicAccess + "\"}";
    }
}