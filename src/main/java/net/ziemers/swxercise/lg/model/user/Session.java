package net.ziemers.swxercise.lg.model.user;

import javax.enterprise.context.SessionScoped;

/**
 * Verwaltet die Session des zurzeit angemeldeten Benutzers.
 */
@SessionScoped
public class Session implements java.io.Serializable {

    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
