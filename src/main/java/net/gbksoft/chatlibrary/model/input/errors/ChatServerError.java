package net.gbksoft.chatlibrary.model.input.errors;

/**
 *
 * Abstract class, that contains main response of input object from server on SERVER_ERROR event
 */

public abstract class ChatServerError<REASON> {
    public static final String ACTION_KEY = "action";

    public static final String UNDEFINED_ERROR = "undefined";
    public static final String CREATE_GROUP_ERROR = "onCreateGroup";
    public static final String CHANGE_GROUP_ERROR = "onChangeGroup";
    public static final String SET_USER_STATUS_ERROR = "onSetUserStatus";
    public static final String ADD_USER_ERROR = "onAddUser";
    public static final String USER_IS_WRITING = "onUserIsWriting";
    public static final String SEND_MESSAGE = "onSendMessage";
    public static final String GET_HISTORY = "onGetHistory";
    public static final String GET_HISTORY_SPECIAL = "onGetHistorySpecial";
    public static final String GET_USERS = "onGetUsers";
    public static final String GET_USERS_NOT_IN_GROUP = "onGetUsers:notInGroup";
    public static final String GET_USERS_ALL_NOT_IN_GROUP = "onGetUsers:allNotInGroup";
    public static final String GET_USERS_ALL_REGISTERED = "onGetUsers:allRegistered";
    public static final String SEND_TO_USER = "sendToUser";
    public static final String SEND_TO_GROUP = "sendToGroup";
    public static final String SEND_TO_ALL = "sendToAll";
    public static final String SET_READED = "onSetReaded";
    public static final String CHECK_UNREAD = "_checkUnread";
    public static final String CHANGE_USERS_ROLES = "onChangeUsersRoles";
    public static final String RESTRICT_USERS = "onRestrictUsers";
    public static final String DELETE_MESSAGE = "onDeleteMessage";
    public static final String EDIT_MESSAGE = "onEditMessage";
    public static final String BLOCK_GROUP = "onBlockGroup";
    public static final String UNBLOCK_GROUP = "onUnblockGroup";
    public static final String STORE_FILE = "onStoreFile";
    public static final String SET_REQUEST_STATUS = "onSetMessageStatus";
    public static final String REMOVE_FILE = "onRemoveFile";

    protected String module;
    protected String action;
    protected String type;
    protected REASON reason;

    public String getType() {
        return type;
    }

    public String getModule() {
        return module;
    }

    public String getAction() {
        return action;
    }

    public REASON getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "{module: \"" + module + "\", action: \"" + action + "\", type: \"" + type + "\"," +
                "reason: " + getReason() + "}";
    }
}