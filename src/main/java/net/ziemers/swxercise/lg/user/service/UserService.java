package net.ziemers.swxercise.lg.user.service;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.ziemers.swxercise.db.dao.user.UserDao;
import net.ziemers.swxercise.lg.model.user.Profile;
import net.ziemers.swxercise.lg.model.user.Role;
import net.ziemers.swxercise.lg.model.user.User;
import net.ziemers.swxercise.lg.user.dto.UserDto;
import net.ziemers.swxercise.lg.user.enums.RightState;

/**
 * Diese Klasse stellt alle Dienste im Kontext einer Benutzerverwaltung zur Verfügung.
 */
@Stateless
public class UserService {

    @Inject
    private UserDao dao;

    @Inject
    private SessionContext sessionContext;

    /**
     * Meldet den Benutzer im SessionContext an.
     *
     * @param dto das {@link UserDto} enthält die Eigenschaften des anzumeldenden Benutzers
     * @return <code>true</code>, wenn die Anmeldung erfolgreich war.
     */
    public boolean loginUser(UserDto dto) {
        final User user = dao.findByUsername(dto.getUsername());
        return user != null && user.getProfile().isValidPassword(dto.getPassword()) && sessionContext.login(user);
    }

    /**
     * Meldet den Benutzer vom SessionContext ab.
     *
     * @return <code>true</code>, wenn die Abmeldung erfolgreich war.
     */
    public boolean logoutUser() {
        return sessionContext.logout();
    }

    /**
     * Findet den Benutzer mit der übergebenen Id.
     *
     * @param id die Id des gesuchten Benutzes.
     * @return den gesuchten Benutzer, oder <code>null</code>, falls ein solcher nicht existiert.
     */
    public User findUser(final Long id) {
        return dao.findById(id);
    }

    /**
     * Findet den zurzeit angemeldeten Benutzer.
     *
     * @return den gesuchten Benutzer, oder <code>null</code>, falls ein solcher nicht existiert.
     */
    public User findUser() {
        final User user = sessionContext.getUser();
        if (user != null) {
            return dao.findById(user.getId());
        }
        return null;
    }

    /**
     * Findet alle existierenden Benutzer.
     *
     * @return alle Benutzer, oder eine leere Collection, falls keine existieren.
     */
    public Collection<User> findAllUsers() {
        return dao.findAll(User.class);
    }

    /**
     * Erstellt einen neuen Benutzer, sofern noch keiner mit dem selben Benutzernamen existiert.
     * Zwischen der Groß- und Kleinschreibung wird nicht unterschieden.
     *
     * @param dto das {@link UserDto} enthält die Eigenschaften des zu erstellenden Benutzers
     * @return die Id des neuen Benutzers, wenn die Erstellung erfolgreich war.
     */
    public Long createUser(final UserDto dto) {
        // der Benutzer darf natürlich noch nicht existieren :)
        User user = dao.findByUsername(dto.getUsername());
        if (user == null) {
            final Profile profile = new Profile(dto.getUsername(), dto.getPassword());

            // wir füllen das User-Objekt mit Method Chaining
            user = new User(dto.getFirstname(), dto.getLastname())
                    .withProfile(profile);

            return dao.save(user);
        }
        return null;
    }

    /**
     * Aktualisiert den Benutzer mit der übergebenen Id.
     *
     * @param id  die Id des zu aktualisierenden Benutzers
     * @param dto das {@link UserDto} enthält die Eigenschaften des zu aktualisierenden Benutzers
     * @return <code>true</code>, wenn das Aktualisieren des Benutzers erfolgreich war.
     */
    public boolean updateUser(final Long id, final UserDto dto) {
        final User user = dao.findById(id);
        if (user != null) {
            // TODO noch zu implementieren
            return false;
        }
        return false;
    }

    /**
     * Aktualisiert den zurzeit angemeldeten Benutzer.
     *
     * @param dto das {@link UserDto} enthält die Eigenschaften des zu aktualisierenden Benutzers
     * @return <code>true</code>, wenn das Aktualisieren des Benutzers erfolgreich war.
     */
    public boolean updateUser(final UserDto dto) {
        // ist zurzeit ein Benutzer angemeldet, können wir ihn aktualisieren
        final User user = sessionContext.getUser();
        if (user != null) {
            // TODO noch zu implementieren
            return false;
        }
        return false;
    }

    /**
     * Löscht den Benutzer mit der übergebenen Id.
     *
     * @param id die Id des zu löschenden Benutzers
     */
    public User deleteUser(final Long id) {
        return dao.remove(User.class, id);
    }

    /**
     * Löscht den zurzeit angemeldeten Benutzer.
     *
     * @return <code>true</code>, wenn das Löschen des angemeldeten Benutzers erfolgreich war.
     */
    public boolean deleteUser() {
        // ist zurzeit ein Benutzer angemeldet, können wir ihn löschen
        final User user = sessionContext.getUser();
        return user != null && dao.remove(User.class, user.getId()) != null;
    }

    /**
     * Liefert das {@link User}-Objekt des zurzeit angemeldeten Benutzers zurück. Das ist derjenige
     * Benutzer, der im {@link SessionContext}-Objekt hinterlegt ist.
     *
     * @return das User-Objekt des zurzeit angemeldeten Benutzers.
     */
    public User getSessionContextUser() {
        return sessionContext.getUser();
    }

    /**
     * Prüft, ob der Benutzer des {@link SessionContext}s ein Recht aus der Liste der übergebenen Rechte besitzt.
     *
     * @param rightsSet die Rechteliste
     * @return <code>true</code>, falls der Benutzer eines der übergebenen Rechte besitzt.
     */
    public boolean isUserAllowed(final Set<String> rightsSet) {
        final User user = getSessionContextUser();

        // Rechte, die nur ein nicht-angemeldeter Benutzer besitzen kann
        if (user == null) {
            if (rightsSet.contains(RightState.Constants.NOT_LOGGED_IN)) {
                return true;
            }
        }

        // Rechte, die nur ein angemeldeter Benutzer besitzen kann
        else {
            if (rightsSet.contains(RightState.Constants.LOGGED_IN)) {
                return true;
            }
            // besitzt dieser Benutzer eine zugewiesene Rolle?
            final Role role = user.getProfile().getRole();
            if (role != null) {
                // sämtliche Rechte iterieren, die den Benutzer berechtigen würden, um zu ermitteln,
                // ob der Benutzer eines dieser Rechte besitzt
                for (String right : rightsSet) {
                    // besitzt der Benutzer das betrachtete Recht?
                    if (role.hasRight(RightState.getByName(right))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
