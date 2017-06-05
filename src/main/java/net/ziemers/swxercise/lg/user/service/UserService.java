package net.ziemers.swxercise.lg.user.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.ziemers.swxercise.db.dao.user.UserDao;
import net.ziemers.swxercise.lg.model.user.Profile;
import net.ziemers.swxercise.lg.model.user.Session;
import net.ziemers.swxercise.lg.model.user.User;
import net.ziemers.swxercise.lg.user.dto.UserDto;

@Stateless
public class UserService {

    @Inject
    private UserDao dao;

    @Inject
    private Session session;

    public boolean loginUser(UserDto dto) {
        final User user = dao.findByUsername(dto.getUsername());

        if (user != null && user.getProfile().isValidPassword(dto.getPassword())) {
            session.setUser(user);
            return true;
        }
        return false;
    }

    public boolean logoutUser() {
        session.setUser(null);
        return session.getUser() == null;
    }

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

    public User getSessionUser() {
        return session.getUser();
    }

}
