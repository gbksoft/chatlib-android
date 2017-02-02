package net.gbksoft.chatlibrary.model.input.status;

import net.gbksoft.chatlibrary.model.input.User;

/**
 *
 * Input object from server on USER_IS_WRITING event
 * contains getUser method.
 */

public class UserIsWriting {
    private User user;

    public User getUser() {
        return user;
    }
}