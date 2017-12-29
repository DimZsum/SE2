package net.ziemers.swxercise.lg.user.service;

import javax.enterprise.context.SessionScoped;

import net.ziemers.swxercise.lg.model.user.User;

/**
 * Verwaltet den SessionContext des zurzeit angemeldeten Benutzers.
 */
@SessionScoped
public class SessionContext implements java.io.Serializable {

	private static final long serialVersionUID = 8624253586553865146L;

	private User user = null;

	private String sessionId = "";

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    public String getSessionId() { return sessionId; }

    private void setSessionId(String sessionId) { this.sessionId = sessionId; }

    /**
     * Meldet einen Benutzer in diesem Session-Kontext an.
     *
     * @param user das {@link User}-Objekt des Benutzers dieses Session-Kontexts
     * @param sessionId die Session-Id dieser Benutzer-Session
     * @return Liefert <code>true</code> zurück, wenn der Benutzer am Session-Kontext angemeldet werden konnte.
     */
    public boolean login(final User user, final String sessionId) {
        if (getUser() == null) {
            setUser(user);
            setSessionId(sessionId);
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
