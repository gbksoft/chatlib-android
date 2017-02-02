package net.gbksoft.chatlibrary.request;

import net.gbksoft.chatlibrary.ChatWebSocket;
import net.gbksoft.chatlibrary.model.output.History;

/**
 *
 * Remove History request logic from ChatWebSocket class.
 */

public class ChatHistoryRequest {
    public static final String GROUP_TYPE = "group";
    public static final String USER_TYPE = "user";

    private ChatWebSocket.IChatWebSocketRequestListener mRequestListener;

    public ChatHistoryRequest(ChatWebSocket.IChatWebSocketRequestListener requestListener) {
        if (requestListener == null) {
            throw new IllegalArgumentException("IChatWebSocketRequestListener cannot ne null");
        }
        mRequestListener = requestListener;
    }

    /**
     * Get user`s history
     *
     * @param type type of user`s history. Server required 'user' or 'group'
     * @param typeId id of the user or of the group
     */
    public void getHistory(String type, String typeId) {
        mRequestListener.createRequest(ChatWebSocket.GET_HISTORY,
                new History(type, typeId).toJSONObject());
    }

    /**
     * Get user`s history
     *
     * @param type type of user`s history. Server required 'user' or 'group'
     * @param typeId id of the user or of the group
     * @param from date from which required history
     */
    public void getHistory(String type, String typeId, long from) {
        mRequestListener.createRequest(ChatWebSocket.GET_HISTORY,
                new History(type, typeId, from).toJSONObject());
    }

    /**
     * Get user`s history
     *
     * @param type type of user`s history. Server required 'user' or 'group'
     * @param typeId id of the user or of the group
     * @param from date from which required history
     * @param byTime date to which required history
     */
    public void getHistory(String type, String typeId, long from, long byTime) {
        mRequestListener.createRequest(ChatWebSocket.GET_HISTORY,
                new History(type, typeId, from, byTime).toJSONObject());
    }

    /**
     * Get group history (available for user with admin role)
     *
     * @param groupId id or of the group
     */
    public void getHistorySpecial(String groupId) {
        mRequestListener.createRequest(ChatWebSocket.GET_HISTORY_SPECIAL,
                new History(GROUP_TYPE, groupId).toJSONObject());
    }
    /**
     * Get group history (available for user with admin role)
     *
     * @param groupId id of the group
     * @param from date from which required history
     */
    public void getHistorySpecial(String groupId, long from) {
        mRequestListener.createRequest(ChatWebSocket.GET_HISTORY_SPECIAL,
                new History(GROUP_TYPE, groupId, from).toJSONObject());
    }

    /**
     * Get group history (available for user with admin role)
     *
     * @param groupId id of the group
     * @param from date from which required history
     * @param byTime date to which required history
     */
    public void getHistorySpecial(String groupId, long from, long byTime) {
        mRequestListener.createRequest(ChatWebSocket.GET_HISTORY_SPECIAL,
                new History(GROUP_TYPE, groupId, from, byTime).toJSONObject());
    }
}