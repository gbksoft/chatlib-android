package net.gbksoft.chatlibrary.model.input;

/**
 *
 * Input object from server on FILE_IS_STORED event
 * contains get methods.
 */

public class FileIsStored extends Preview {
    private Preview preview;

    public Preview getPreview() {
        return preview;
    }

    @Override
    public String toString() {
        return "{url: \"" + url + "\", contentType: \"" + contentType + "\", " +
                "contentLength: " + contentLength + ", publicAccess: \"" + publicAccess + "\", " +
                "preview: {" + preview + "}}";
    }
}