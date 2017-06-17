package net.ziemers.swxercise.lg.user.service;

import net.ziemers.swxercise.db.dao.GenericDao;
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
    private GenericDao dao;

    public Collection<Role> findAllRoles() {
        // TODO noch zu implementieren
        return null;
    }

    public Long createRole(final RoleDto dto) {
        final Role role = new Role(dto.getName(), dto.getRight());
        return dao.save(role);
    }

}
