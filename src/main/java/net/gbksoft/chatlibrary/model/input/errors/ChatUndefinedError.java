package net.gbksoft.chatlibrary.model.input.errors;

/**
 *
 * Input object from server on SERVER_ERROR event undefined type
 */

public class ChatUndefinedError extends
        ChatServerError<ChatUndefinedError.UndefinedErrorReason> {

    public class UndefinedErrorReason {
        private Object err;

        public Object getErr() {
            return err;
        }

        @Override
        public String toString() {
            return "{err: \"" + err + "\"";
        }
    }
}
