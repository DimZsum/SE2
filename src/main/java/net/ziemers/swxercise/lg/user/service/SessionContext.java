package net.ziemers.swxercise.lg.user.service;

import javax.enterprise.context.SessionScoped;

import net.ziemers.swxercise.lg.model.user.User;

/**
 * Verwaltet den SessionContext des zurzeit angemeldeten Benutzers.
 */
@SessionScoped
public class SessionContext implements java.io.Serializable {

    private User user = null;

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    public boolean login(final User user) {
        if (getUser() == null) {
            setUser(user);
            return true;
        }
        return false;
    }

    public boolean logout() {
        if (getUser() != null) {
            setUser(null);
            return true;
        }
        return false;
    }

}
