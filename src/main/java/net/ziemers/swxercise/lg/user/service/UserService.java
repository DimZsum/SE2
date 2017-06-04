package net.ziemers.swxercise.lg.user.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.ziemers.swxercise.db.dao.GenericDao;
import net.ziemers.swxercise.lg.user.Profile;
import net.ziemers.swxercise.lg.user.User;
import net.ziemers.swxercise.lg.user.dto.UserDto;

@Stateless
public class UserService {

    @Inject
    private GenericDao genericDao;

    public User findUser(final Long id) { return genericDao.findById(User.class, id); }

    public Collection<User> findAllUsers() {
        return genericDao.findAll(User.class);
    }

    public Long saveUser(final UserDto userDto) {
        final Profile profile = new Profile(userDto.getUsername(), userDto.getPassword());

        // wir füllen das User-Objekt mit Method Chaining
        final User user = new User(userDto.getFirstname(), userDto.getLastname())
                .withProfile(profile);

        return genericDao.save(user);
    }

}
