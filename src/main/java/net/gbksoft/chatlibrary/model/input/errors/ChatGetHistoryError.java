package net.gbksoft.chatlibrary.model.input.errors;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Input object from server on SERVER_ERROR event onGetHistory type
 */

public class ChatGetHistoryError extends
        ChatServerError<ChatGetHistoryError.GetHistoryErrorReason> {

    public class GetHistoryErrorReason {
        private String userId;
        private String type;
        private String typeId;
        private String from;
        private String byTime;
        @SerializedName("SQL")
        private Object sql;
        private Object err;

        public String getUserId() {
            return userId;
        }

        public String getType() {
            return type;
        }

        public String getTypeId() {
            return typeId;
        }

        public String getFrom() {
            return from;
        }

        public String getByTime() {
            return byTime;
        }

        public Object getSql() {
            return sql;
        }

        public Object getErr() {
            return err;
        }

        @Override
        public String toString() {
            return "{userId: \"" + userId + "\", type: \"" + type + "\"," +
                    "typeId: \"" + typeId + "\" from: \"" + from + "\" byTime: \"" + byTime + "\"" +
                    "sql: \"" + sql + "\" err: \"" + err + "\"}";
        }
    }
}