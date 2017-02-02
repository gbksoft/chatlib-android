package net.gbksoft.chatlibrary.log;

/**
 *
 * The log listener interface for Chat library
 */

public interface IChatLogListener {
    void i(String tag, String string);

    void e(String tag, String string);

    void d(String tag, String string);

    void v(String tag, String string);

    void w(String tag, String string);

    void e(String tag, String msg, Throwable throwable);
}