package net.gbksoft.chatlibrary.request;

import net.gbksoft.chatlibrary.ChatWebSocket;
import net.gbksoft.chatlibrary.model.output.Request;
import net.gbksoft.chatlibrary.model.output.RequestStatus;

import java.util.ArrayList;

/**
 *
 * Remove request logic from ChatWebSocket class.
 */

public class ChatRequest {
    private ChatWebSocket.IChatWebSocketRequestListener mRequestListener;

    public ChatRequest(ChatWebSocket.IChatWebSocketRequestListener requestListener) {
        if (requestListener == null) {
            throw new IllegalArgumentException("IChatWebSocketRequestListener cannot ne null");
        }
        mRequestListener = requestListener;
    }

    /**
     * Create request for ban user
     *
     * @param groupId id of a room
     * @param userId id of user, that needs to be banned
     * @param reason reason of ban
     */
    public void requestForBan(String groupId, String userId, String reason) {
        mRequestListener.createRequest(
                ChatWebSocket.REQUEST_FOR_BAN,
                new Request(groupId, userId, reason).toJSONObject());
    }

    /**
     * Create request for ban users
     *
     * @param groupId id of a room
     * @param usersIds id of users, that needs to be banned
     * @param reason reason of ban
     */
    public void requestForBan(String groupId, ArrayList<String> usersIds, String reason) {
        if (usersIds == null) {
            mRequestListener.createRequest(
                    ChatWebSocket.REQUEST_FOR_BAN,
                    new Request(groupId, "", reason).toJSONObject());
        } else {
            for (String userId : usersIds) {
                mRequestListener.createRequest(
                        ChatWebSocket.REQUEST_FOR_BAN,
                        new Request(groupId, userId, reason).toJSONObject());
            }
        }
    }

    /**
     * Create request for unban user
     *
     * @param groupId id of a room
     * @param userId id of user, that needs to be unbanned
     * @param reason reason of unban
     */
    public void requestForUnban(String groupId, String userId, String reason) {
        mRequestListener.createRequest(
                ChatWebSocket.REQUEST_FOR_UNBAN,
                new Request(groupId, userId, reason).toJSONObject());
    }

    /**
     * Create request for unban users
     *
     * @param groupId id of a room
     * @param usersIds id of users, that needs to be unbanned
     * @param reason reason of unban
     */
    public void requestForUnban(String groupId, ArrayList<String> usersIds, String reason) {
        if (usersIds == null) {
            mRequestListener.createRequest(
                    ChatWebSocket.REQUEST_FOR_UNBAN,
                    new Request(groupId, "", reason).toJSONObject());
        } else {
            for (String userId : usersIds) {
                mRequestListener.createRequest(
                        ChatWebSocket.REQUEST_FOR_UNBAN,
                        new Request(groupId, userId, reason).toJSONObject());
            }
        }
    }

    /**
     * Create request for block group
     *
     * @param groupId id of the group, that needs to be blocked
     * @param reason reason of block
     */
    public void requestForBlockGroup(String groupId, String reason) {
        mRequestListener.createRequest(
                ChatWebSocket.REQUEST_FOR_BLOCK_GROUP,
                new Request(groupId, reason).toJSONObject());
    }

    /**
     * Create request for block groups
     *
     * @param groupsIds id of the groups, that needs to be blocked
     * @param reason reason of the block
     */
    public void requestForBlockGroups(ArrayList<String> groupsIds, String reason) {
        if (groupsIds == null) {
            mRequestListener.createRequest(
                    ChatWebSocket.REQUEST_FOR_BLOCK_GROUP,
                    new Request("", reason).toJSONObject());
        } else {
            for (String groupId : groupsIds) {
                mRequestListener.createRequest(
                        ChatWebSocket.REQUEST_FOR_BLOCK_GROUP,
                        new Request(groupId, reason).toJSONObject());
            }
        }
    }

    /**
     * Create request for unblock group
     *
     * @param groupId id of the group, that needs to be unblocked
     * @param reason reason of unblocked request
     */
    public void requestForUnblockGroup(String groupId, String reason) {
        mRequestListener.createRequest(
                ChatWebSocket.REQUEST_FOR_UNBLOCK_GROUP,
                new Request(groupId, reason).toJSONObject());
    }

    /**
     * Create request for ban users
     *
     * @param groupsIds id of the groups, that needs to be  unblocked
     * @param reason reason of unblocked request
     */
    public void requestForUnblockGroups(ArrayList<String> groupsIds, String reason) {
        if (groupsIds == null) {
            mRequestListener.createRequest(
                    ChatWebSocket.REQUEST_FOR_UNBLOCK_GROUP,
                    new Request("", reason).toJSONObject());
        } else {
            for (String groupId : groupsIds)
                mRequestListener.createRequest(
                        ChatWebSocket.REQUEST_FOR_UNBLOCK_GROUP,
                        new Request(groupId, reason).toJSONObject());
        }
    }

    /**
     * Create request for join to the closed group
     *
     * @param groupId id of the group, that user wants to join
     * @param reason reason of join group request
     */
    public void requestForJoin(String groupId, String reason) {
        mRequestListener.createRequest(
                ChatWebSocket.REQUEST_FOR_JOIN,
                new Request(groupId, reason).toJSONObject());
    }

    /**
     * Create request for join to the closed groups
     *
     * @param groupsIds id of the groups, that user wants to join
     * @param reason reason of join group request
     */
    public void requestForJoin(ArrayList<String> groupsIds, String reason) {
        if (groupsIds == null) {
            mRequestListener.createRequest(
                    ChatWebSocket.REQUEST_FOR_JOIN,
                    new Request("", reason).toJSONObject());
        } else {
            for (String groupId : groupsIds) {
                mRequestListener.createRequest(
                        ChatWebSocket.REQUEST_FOR_JOIN,
                        new Request(groupId, reason).toJSONObject());
            }
        }
    }

    /**
     * Approve or reject request (for moderators)
     *
     * @param requestId id of the request
     * @param isAccept is the request accepted
     */
    public void setRequestStatus(String requestId, boolean isAccept) {
        mRequestListener.createRequest(
                ChatWebSocket.SET_REQUEST_STATUS,
                new RequestStatus(requestId, isAccept).toJSONObject());
    }
}