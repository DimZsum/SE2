package net.ziemers.swxercise.db.dao.user;

import net.ziemers.swxercise.db.dao.GenericDao;
import net.ziemers.swxercise.lg.model.user.User;

import javax.ejb.Stateless;

@Stateless
public class UserDao extends GenericDao {

    public User findById(final Long id) {
        User user = null;

        try {
            // ermittelt den ersten Datensatz mit der gesuchten Id, auch wenn er sich nicht im Persistence Context befindet
            user = (User) entityManager.createNamedQuery("User.findById").setParameter("id", id).getSingleResult();
        } catch(Exception e) {
			/* nix */
        }
        return user;
    }

}
