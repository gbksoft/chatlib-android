package net.gbksoft.chatlibrary.model.input.errors;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Input object from server on AUTH_ERROR event
 */

public class ChatAuthTokenError extends
        ChatServerError<ChatAuthTokenError.ChatAuthTokenErrorReason> {

    public class ChatAuthTokenErrorReason {
        @SerializedName("error")
        private String errorString;
        private String authToken;

        public String getError() {
            return errorString;
        }

        public String getAuthToken() {
            return authToken;
        }

        @Override
        public String toString() {
            return "{error: \"" + errorString + "\", authToken: \"" + authToken + "\"}";
        }
    }
}