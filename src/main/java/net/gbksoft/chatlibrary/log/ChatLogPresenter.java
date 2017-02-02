package net.gbksoft.chatlibrary.log;

/**
 *
 * The log presenter class for Chat Library
 * Contains listener and call listener methods
 *
 * ChatWebSocket call methods of ChatLogPresenter and delegate this methods to the presenter.
 * If ChatLogListener is not null, presenter delegate calls of log methods to the ChatLogListener
 */

public class ChatLogPresenter {
    private IChatLogListener mChatLogListener;

    public void setChatLogListener(IChatLogListener chatLogListener) {
        mChatLogListener = chatLogListener;
    }

    public void removeLogListener() {
        mChatLogListener = null;
    }

    public void i(String tag, String string) {
        if (mChatLogListener != null) {
            mChatLogListener.i(tag, string);
        }
    }

    public void e(String tag, String string) {
        if (mChatLogListener != null) {
            mChatLogListener.e(tag, string);
        }
    }

    public void d(String tag, String string) {
        if (mChatLogListener != null) {
            mChatLogListener.d(tag, string);
        }
    }

    public void v(String tag, String string) {
        if (mChatLogListener != null) {
            mChatLogListener.v(tag, string);
        }
    }

    public void w(String tag, String string) {
        if (mChatLogListener != null) {
            mChatLogListener.w(tag, string);
        }
    }

    public void e(String tag, String msg, Throwable throwable) {
        if (mChatLogListener != null) {
            mChatLogListener.e(tag, msg, throwable);
        }
    }
}