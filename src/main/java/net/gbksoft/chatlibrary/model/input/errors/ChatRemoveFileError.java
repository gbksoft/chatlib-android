package net.gbksoft.chatlibrary.model.input.errors;

/**
 *
 * Input object from server on SERVER_ERROR event onRemoveFile type
 */

public class ChatRemoveFileError extends
        ChatServerError<ChatRemoveFileError.ChatRemoveFileErrorReason> {

    public class ChatRemoveFileErrorReason {
        private String url;

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "{url: \"" + url + "\"}";
        }
    }
}