package net.gbksoft.chatlibrary.connection;

/**
 *
 * Class contains interface and adapter, that returns connection status from server to the dependency project
 */

public class ChatConnectionListener {

    public interface ISocketConnectionListener {
        void onConnect(Object connectObject);
        void onReconnect(Object reconnectObject);
        void onDisconnect(Object disconnectObject);
        void onConnectionError(Object connectionErrorObject);
        void onError(Object errorObject);
        void onEventConnectTimeout(Object eventConnectTimeoutObject);
    }

    public static class ISocketConnectionListenerAdapter implements ISocketConnectionListener {

        @Override
        public void onConnect(Object connectObject) {

        }

        @Override
        public void onReconnect(Object reconnectObject) {

        }

        @Override
        public void onDisconnect(Object disconnectObject) {

        }

        @Override
        public void onConnectionError(Object connectionErrorObject) {

        }

        @Override
        public void onError(Object errorObject) {

        }

        @Override
        public void onEventConnectTimeout(Object eventConnectTimeoutObject) {

        }
    }
}