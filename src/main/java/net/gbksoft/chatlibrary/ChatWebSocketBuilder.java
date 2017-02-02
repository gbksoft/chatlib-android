package net.gbksoft.chatlibrary;

/**
 *
 * The builder class for Chat Library
 * Contains methods for create and config ChatWebSocket class
 */

public class ChatWebSocketBuilder {
    private int mPort;
    private String mChatFrontPath;
    private String mChatFrontScheme;
    private String mChatSocketNamespace;

    public ChatWebSocketBuilder() {

    }

    /**
     * Sets port number for connection
     * @param port port number
     * @return {@link ChatWebSocketBuilder} instance of builder
     */

    public ChatWebSocketBuilder setPort(int port) {
        mPort = port;
        return this;
    }

    /**
     * Sets chat url connection scheme (http, https ...)
     *
     * @param сhatFrontScheme String url scheme
     * @return {@link ChatWebSocketBuilder} instance of builder
     */

    public ChatWebSocketBuilder setChatFrontScheme(String сhatFrontScheme) {
        mChatFrontScheme = сhatFrontScheme;
        return this;
    }

    /**
     * Sets server domain
     *
     * @param chatFrontPath String server domain
     * @return {@link ChatWebSocketBuilder} instance of builder
     */

    public ChatWebSocketBuilder setChatFrontPath(String chatFrontPath) {
        mChatFrontPath = chatFrontPath;
        return this;
    }

    /**
     * Sets additional namespace
     *
     * @param chatSocketNamespace String socket namespace
     * @return {@link ChatWebSocketBuilder} instance of builder
     */
    public ChatWebSocketBuilder setChatSocketNamespace(String chatSocketNamespace) {
        mChatSocketNamespace = chatSocketNamespace;
        return this;
    }

    public int getPort() {
        return mPort;
    }

    public String getChatFrontPath() {
        return mChatFrontPath;
    }

    public String getChatFrontScheme() {
        return mChatFrontScheme;
    }

    public String getChatSocketNamespace() {
        return mChatSocketNamespace;
    }

    public ChatWebSocket build() {
        return new ChatWebSocket(this);
    }
}