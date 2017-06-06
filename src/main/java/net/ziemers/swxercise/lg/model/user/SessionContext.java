package net.ziemers.swxercise.lg.model.user;

import javax.enterprise.context.SessionScoped;

/**
 * Verwaltet den SessionContext des zurzeit angemeldeten Benutzers.
 */
@SessionScoped
public class SessionContext implements java.io.Serializable {

    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
