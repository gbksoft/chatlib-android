package net.gbksoft.chatlibrary.model.input.errors;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Input object from server on SERVER_ERROR event onCreateGroup type
 */

public class ChatCreateGroupError extends
        ChatServerError<ChatCreateGroupError.CreateGroupErrorReason> {

    public class CreateGroupErrorReason {
        @SerializedName("user.id")
        private String userId;
        private String groupDescr;

        public String getUserId() {
            return userId;
        }

        public String getGroupDescr() {
            return groupDescr;
        }

        @Override
        public String toString() {
            return "{userId: \"" + userId + "\", groupDescr: \"" + groupDescr + "\"}";
        }
    }
}