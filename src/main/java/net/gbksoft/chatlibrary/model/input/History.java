package net.gbksoft.chatlibrary.model.input;

import net.gbksoft.chatlibrary.utils.StringUtils;

import java.util.ArrayList;

/**
 *
 * Input object from server on HISTORY event
 * contains get methods.
 */

public class History extends net.gbksoft.chatlibrary.model.output.History {
    private ArrayList<Message> messages;

    public String getType() {
        return type;
    }

    public String getTypeId() {
        return typeId;
    }

    public long getFrom() {
        return from;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "{type: \"" + type + "\", typeId: \"" + typeId + "\", from: " + from + ", " +
                "messages: [" + StringUtils.getStringFromArray(messages) + "]}";
    }
}