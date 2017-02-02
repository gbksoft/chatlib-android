package net.gbksoft.chatlibrary;

import net.gbksoft.chatlibrary.connection.ChatConnectionListener;

/**
 *
 * The connection presenter class for Chat Library
 * Contains listener and call listener methods
 *
 * ChatWebSocket call methods of ChatConnectionPresenter and delegate connection methods to the presenter.
 * If ISocketConnectionListener is not null, presenter delegate calls of this methods to the ChatLogListener
 */

public class ChatConnectionPresenter {

    private ChatConnectionListener.ISocketConnectionListener mSocketConnectionListener;

    void setSocketConnectionListener(
            ChatConnectionListener.ISocketConnectionListener socketConnectionListener) {
        mSocketConnectionListener = socketConnectionListener;
    }

    void removeSocketConnectionListener() {
        mSocketConnectionListener = null;
    }

    void onConnect(Object connectObject) {
        if (mSocketConnectionListener != null) {
            mSocketConnectionListener.onConnect(connectObject);
        }
    }

    void onReconnect(Object reconnectObject) {
        if (mSocketConnectionListener != null) {
            mSocketConnectionListener.onReconnect(reconnectObject);
        }
    }

    void onDisconnect(Object disconnectObject) {
        if (mSocketConnectionListener != null) {
            mSocketConnectionListener.onDisconnect(disconnectObject);
        }
    }

    void onConnectionError(Object connectionErrorObject) {
        if (mSocketConnectionListener != null) {
            mSocketConnectionListener.onConnectionError(connectionErrorObject);
        }
    }

    void onError(Object errorObject) {
        if (mSocketConnectionListener != null) {
            mSocketConnectionListener.onError(errorObject);
        }
    }

    void onEventConnectTimeout(Object eventConnectTimeout) {
        if (mSocketConnectionListener != null) {
            mSocketConnectionListener.onEventConnectTimeout(eventConnectTimeout);
        }
    }
}