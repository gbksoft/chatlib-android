package net.gbksoft.chatlibrary.model.input.errors;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Input object from server on SERVER_ERROR event onGetUsers type
 */

public class ChatGetUsersError extends
        ChatServerError<ChatGetUsersError.GetUsersErrorReason> {

    public class GetUsersErrorReason {
        private String userDesct;
        @SerializedName("usersDescr.notInGroup")
        private String usersDescrNotInGroup;
        @SerializedName("err")
        private Object sqlError;

        public String getUserDesct() {
            if (TextUtils.isEmpty(userDesct)) {
                return usersDescrNotInGroup;
            }
            return userDesct;
        }

        public Object getSqlError() {
            return sqlError;
        }

        @Override
        public String toString() {
            return "{userDesct: \"" + getUserDesct() + "\", sqlError: \"" + sqlError + "\"}";
        }
    }
}