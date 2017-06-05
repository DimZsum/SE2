package net.ziemers.swxercise.lg.user.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.ziemers.swxercise.db.dao.user.UserDao;
import net.ziemers.swxercise.lg.model.user.Profile;
import net.ziemers.swxercise.lg.model.user.User;
import net.ziemers.swxercise.lg.user.dto.UserDto;

@Stateless
public class UserService {

    @Inject
    private UserDao dao;

    public User findUser(final Long id) {
        return dao.findById(id);
    }

    public Collection<User> findAllUsers() {
        return dao.findAll(User.class);
    }

    public Long createUser(final UserDto userDto) {
        final Profile profile = new Profile(userDto.getUsername(), userDto.getPassword());

        // wir füllen das User-Objekt mit Method Chaining
        final User user = new User(userDto.getFirstname(), userDto.getLastname())
                .withProfile(profile);

        return dao.save(user);
    }

    public void deleteUser(final Long id) {
        dao.remove(User.class, id);
    }

}
