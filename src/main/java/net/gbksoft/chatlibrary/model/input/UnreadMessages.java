package net.gbksoft.chatlibrary.model.input;

import net.gbksoft.chatlibrary.utils.StringUtils;

import java.util.ArrayList;

/**
 *
 * Input object from server on UNREAD event
 * contains get methods.
 */

public class UnreadMessages {
    public ArrayList<FromSender> fromSenders;

    public ArrayList<FromSender> getFromSenders() {
        return fromSenders;
    }

    @Override
    public String toString() {
        return "fromSenders : {" + StringUtils.getStringFromArray(fromSenders) + "}";
    }

    public class FromSender {
        private String id;
        private int count;

        public String getId() {
            return id;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "id: \"" + id + "\", count: " + count;
        }
    }
}