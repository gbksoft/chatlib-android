package net.gbksoft.chatlibrary;

import android.os.Handler;

import net.gbksoft.chatlibrary.model.output.Message;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * The message presenter class for Chat Library
 * Save the outcoming messages to the DB if the input message callbacks are not called in a
 *  CHECK_DELAYED time and send all unsended messages to the server on the connect event.
 */

class ChatMessageSendPresenter {
    private static final int CHECK_DELAYED = 5000;

    private Date mDate = new Date();
    private LinkedHashSet<Message> mOutputMessageList = new LinkedHashSet<>();

    private Handler mHandler;
    private ChatWebSocket mChatWebSocket;

    ChatMessageSendPresenter(ChatWebSocket chatWebSocket) {
        mChatWebSocket = chatWebSocket;

        mHandler = new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {
                Message outputMessage = (Message) msg.obj;
                if (!mOutputMessageList.contains(outputMessage)) {
                    return;
                }
                outputMessage.save();
            }
        };

    }

    void updateUnreadMessagesList() {
        mOutputMessageList.addAll(Message.listAll(Message.class));
    }

    Message sendMessage(Message outputMessage) {
        outputMessage.setTimestamp(mDate.getTime());
        mOutputMessageList.add(outputMessage);

        android.os.Message message = new android.os.Message();
        message.obj = outputMessage;

        mHandler.sendMessageDelayed(message, CHECK_DELAYED);
        mChatWebSocket.sendMessageEvent(outputMessage);
        return outputMessage;
    }

    void onNewInputMessage(net.gbksoft.chatlibrary.model.input.Message message) {
        for (Message outputMessage : mOutputMessageList) {
            if (outputMessage.getFrontId() == message.getFrontId()) {
                mOutputMessageList.remove(outputMessage);
                return;
            }
        }
    }

    Set<Message> getUnsendedMessages() {
        return mOutputMessageList;
    }

    void sendAllUnsendedMessages() {
        for (Message outputMessage : mOutputMessageList) {
            sendMessage(outputMessage);
        }
        Message.deleteAll(Message.class);
    }
}