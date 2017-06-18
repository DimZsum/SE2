package net.ziemers.swxercise.db.dao.user;

import net.ziemers.swxercise.db.dao.GenericDao;
import net.ziemers.swxercise.lg.model.user.Role;
import net.ziemers.swxercise.lg.model.user.User;

import javax.ejb.Stateless;

/**
 * Stellt Persistenz-Funktionalität im Kontext der Rollen- und Rechteverwaltung
 * zur Verfügung.
 */
@Stateless
public class RoleDao extends GenericDao {

    /**
     * Findet eine {@link Role} aufgrund ihres Rollennamens.
     *
     * @param name der name der gewünschten Rolle
     * @return den User oder null, falls es keinen gibt.
     */
    public Role findByName(final String name) {
        Role role = null;

        try {
            // ermittelt den ersten Datensatz mit dem gesuchten Rollennamen,
            // auch wenn er sich nicht im Persistence Context befindet
            role = (Role) entityManager.createNamedQuery("Role.findByName")
                    .setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            /* nix */
        }
        return role;
    }

}
