package net.gbksoft.chatlibrary.model.input.errors;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Input object from server on SERVER_ERROR event onSetReaded type
 */

public class ChatSetReadedError extends
        ChatServerError<ChatSetReadedError.ChatSetReadedReason> {

    public class ChatSetReadedReason {
        private String userId;
        private String senderId;
        private String messageIds0;
        @SerializedName("SQL")
        private Object sql;
        private Object err;

        public String getUserId() {
            return userId;
        }

        public String getSenderId() {
            return senderId;
        }

        public String getMessageIds0() {
            return messageIds0;
        }

        public Object getSql() {
            return sql;
        }

        public Object getErr() {
            return err;
        }

        @Override
        public String toString() {
            return "{userId: \"" + userId + "\", senderId: \"" + senderId + "\", " +
                    "messageIds0: \"" + messageIds0 + "\" sql: \"" + sql +
                    " \"err: \"" + err + "\"}";
        }
    }
}