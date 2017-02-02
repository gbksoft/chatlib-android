package net.gbksoft.chatlibrary.model.input.errors;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Input object from server on SERVER_ERROR event _checkUnread type
 */

public class ChatCheckUnreadError extends
        ChatServerError<ChatCheckUnreadError.ChatSetReadedReason> {

    public class ChatSetReadedReason {
        private String recipientId;
        @SerializedName("SQL")
        private Object sql;
        private Object err;

        public String getRecipientId() {
            return recipientId;
        }

        public Object getSql() {
            return sql;
        }

        public Object getErr() {
            return err;
        }

        @Override
        public String toString() {
            return "{recipientId: \"" + recipientId + "\"sql: \"" + sql + "\"err: \"" + err + "\"}";
        }
    }
}