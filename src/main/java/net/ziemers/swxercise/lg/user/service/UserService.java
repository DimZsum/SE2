package net.ziemers.swxercise.lg.user.service;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.ziemers.swxercise.db.dao.user.UserDao;
import net.ziemers.swxercise.lg.model.user.Profile;
import net.ziemers.swxercise.lg.model.user.User;
import net.ziemers.swxercise.lg.user.dto.UserDto;

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
     * Findet alle existierenden Benutzer.
     * @return alle Benutzer, oder eine leere Collection, falls keine existieren.
     */
    public Collection<User> findAllUsers() {
        return dao.findAll(User.class);
    }

    /**
     * Erstellt einen neuen Benutzer, sofern noch keiner mit dem selben Benutzernamen existiert.
     *
     * @param userDto das {@link UserDto} enthält die Eigenschaften des zu erstellenden Benutzers
     * @return die Id des neuen Benutzers, wenn die Erstellung erfolgreich war.
     */
    public Long createUser(final UserDto userDto) {
        User user = dao.findByUsername(userDto.getUsername());
        if (user == null) {
            final Profile profile = new Profile(userDto.getUsername(), userDto.getPassword());

            // wir füllen das User-Objekt mit Method Chaining
            user = new User(userDto.getFirstname(), userDto.getLastname())
                    .withProfile(profile);

            return dao.save(user);
        }
        return null;
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
     * Liefert das {@link User}-Objekt des zurzeit angemeldeten Benutzers zurück.
     * @return das User-Objekt des zurzeit angemeldeten Benutzers.
     */
    public User getSessionUser() {
        return sessionContext.getUser();
    }

    /**
     * Prüft, ob der zurzeit angemeldete Benutzer eine Rolle aus der Liste der übergebenen Rollen besitzt.
     *
     * @param rolesSet die Rollenliste
     * @return <code>true</code>, falls der zurzeit angemeldete Benutzer eine der übergebenen Rollen besitzt.
     */
    public boolean isUserAllowed(final Set<String> rolesSet) {
        final User user = getSessionUser();

        // ist überhaupt ein Benutzer angemeldet?
        if (user != null) {
            // TODO muss noch implementiert werden
            return true;
        }
        return false;
    }
}
