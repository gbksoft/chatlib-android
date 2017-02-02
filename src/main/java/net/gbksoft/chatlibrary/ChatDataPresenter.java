package net.gbksoft.chatlibrary;

import net.gbksoft.chatlibrary.chatdata.ChatDataListeners;
import net.gbksoft.chatlibrary.model.input.*;
import net.gbksoft.chatlibrary.model.input.errors.*;
import net.gbksoft.chatlibrary.model.input.status.*;

import java.util.LinkedList;

/**
 *
 * The connection presenter class for Chat Library
 * Contains listeners and call listeners methods
 *
 * ChatWebSocket call methods of ChatDataPresenter and delegate data methods to the presenter.
 * If ChatDataListener collection is not null, presenter delegate calls of this methods to the
 * all added ChatDataListeners.
 */

class ChatDataPresenter {

    private LinkedList<ChatDataListeners.IChatDataListener>
            mChatDataListenerList = new LinkedList<>();

    void addChatStatusListener(ChatDataListeners.IChatDataListener chatDataListener) {
        mChatDataListenerList.add(chatDataListener);
    }

    void removeChatStatusListener(ChatDataListeners.IChatDataListener chatDataListener) {
        mChatDataListenerList.remove(chatDataListener);
    }

    void onConnected(UserData userData) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onConnected(userData);
            }
        }
    }

    void onJoinedTo(JoinedGroup joinedGroupName) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onJoinedTo(joinedGroupName);
            }
        }
    }

    void onNewGroup(Group group) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onNewGroup(group);
            }
        }
    }

    void onMessageUserReceived(Message inputMessage) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onMessageUserReceived(inputMessage);
            }
        }
    }

    void onMessageGroupReceived(Message inputMessage) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onMessageGroupReceived(inputMessage);
            }
        }
    }

    void onMessageBroadcastReceived(Message inputMessage) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onMessageBroadcastReceived(inputMessage);
            }
        }
    }

    void onUserIn(UserConnectionIn userConnectionIn) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onUserIn(userConnectionIn);
            }
        }
    }

    void onUserOut(UserConnectionOut userConnectionOut) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onUserOut(userConnectionOut);
            }
        }
    }

    void onGroupUsers(GroupUsers data) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onGroupUsers(data);
            }
        }
    }

    void onHistoryData(History history) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onHistoryData(history);
            }
        }
    }

    void onUnreadMessages(UnreadMessages unreadMessages) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onUnreadMessages(unreadMessages);
            }
        }
    }

    void onUserIsWriting(User user) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onUserIsWriting(user);
            }
        }
    }

    void onMessagesIsSent(SendStatus sendStatus) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onMessagesIsSent(sendStatus);
            }
        }
    }

    void onMessagesAreRead(ReadStatus readStatus) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onMessagesAreRead(readStatus);
            }
        }
    }

    void onUserStatus(UserStatus userStatus) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onUserStatus(userStatus);
            }
        }
    }

    void onChangeUserRoles(NewRole userRole) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onChangeUserRoles(userRole);
            }
        }
    }

    void onRestrictedRights(RestrictedRights restrictedRights) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onRestrictedRights(restrictedRights);
            }
        }
    }

    void onMessageIsDeleted(MessageIsDeleted message) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onMessageIsDeleted(message);
            }
        }
    }

    void onMessageIsEdited(MessageIsEdited message) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onMessageIsEdited(message);
            }
        }
    }

    void onBlockGroup(GroupIsBlocked response) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onBlockGroup(response);
            }
        }
    }

    void onUnblockGroup(GroupIsUnblocked response) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onUnblockGroup(response);
            }
        }
    }

    void onRequestAccepted(RequestAccepted requestAccepted) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onRequestAccepted(requestAccepted);
            }
        }
    }

    void onRequestForBan(UserRequest response) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onRequestForBan(response);
            }
        }
    }

    void onRequestForUnban(UserRequest response) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onRequestForUnban(response);
            }
        }
    }

    void onRequestForBlockGroup(GroupRequest response) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onRequestForBlockGroup(response);
            }
        }
    }

    void onRequestForUnblockGroup(GroupRequest response) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onRequestForUnblockGroup(response);
            }
        }
    }

    void onRequestForJoin(GroupRequest response) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onRequestForJoin(response);
            }
        }
    }

    void onHistorySpecial(HistorySpecial response) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onHistorySpecial(response);
            }
        }
    }

    void onFileIsStored(FileIsStored response) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onFileIsStored(response);
            }
        }
    }

    void onRequestStatusIsChanged(RequestStatus response) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onRequestStatusIsChanged(response);
            }
        }
    }

    void onMessageAdd(MessageAdd response) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onMessageAdd(response);
            }
        }
    }

    void onFileIsRemoved(String response) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onFileIsRemoved(response);
            }
        }
    }

    void onError(ChatServerError error) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onError(error);
            }
        }
    }

    void onAuthTokenError(ChatAuthTokenError error) {
        for (ChatDataListeners.IChatDataListener listener : mChatDataListenerList) {
            if (listener != null) {
                listener.onAuthTokenError(error);
            }
        }
    }
}