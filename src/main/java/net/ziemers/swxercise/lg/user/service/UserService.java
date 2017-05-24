package net.ziemers.swxercise.lg.user.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.ziemers.swxercise.db.BaseEntity;
import net.ziemers.swxercise.db.dao.GenericDao;
import net.ziemers.swxercise.lg.user.User;

@Stateless
public class UserService {

    @Inject
    private GenericDao genericDao;

    public User findUser(final Long id) {
        return genericDao.findById(User.class, id);
    }

    public Collection<User> findAllUsers() {
        return genericDao.findAll(User.class);
    }

    public <T extends BaseEntity> Long saveUser(final User user) {
        return genericDao.save(user);
    }

}
