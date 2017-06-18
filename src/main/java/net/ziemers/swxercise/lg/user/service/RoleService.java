package net.ziemers.swxercise.lg.user.service;

import net.ziemers.swxercise.db.dao.user.RoleDao;
import net.ziemers.swxercise.lg.model.user.Role;
import net.ziemers.swxercise.lg.user.dto.RoleDto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

/**
 * Diese Klasse stellt alle Dienste im Kontext einer Rollen- und Rechteverwaltung zur Verfügung.
 */
@Stateless
public class RoleService {

    @Inject
    private RoleDao dao;

    /**
     * Findet alle existierenden Rollen.
     *
     * @return alle Rollen, oder eine leere Collection, falls keine existieren.
     */
    public Collection<Role> findAllRoles() { return dao.findAll(Role.class); }

    /**
     * Erstellt eine neue Rolle, sofern noch keine mit dem selben Namen existiert.
     * Zwischen der Groß- und Kleinschreibung wird nicht unterschieden.
     *
     * @param dto das {@link RoleDto} enthält die Eigenschaften der zu erstellenden Rolle
     * @return die Id der neuen Rolle, wenn die Erstellung erfolgreich war.
     */
    public Long createRole(final RoleDto dto) {
        Role role = dao.findByName(dto.getName());
        if (role == null) {
            role = new Role(dto.getName(), dto.getRight());

            return dao.save(role);
        }
        return null;
    }

}
