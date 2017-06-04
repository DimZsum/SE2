package net.ziemers.swxercise.ui;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import net.ziemers.swxercise.lg.user.Profile;
import net.ziemers.swxercise.lg.user.User;
import net.ziemers.swxercise.lg.user.dto.UserDto;
import net.ziemers.swxercise.lg.user.service.UserService;

@ApplicationScoped
@Path(RestViewController.webContextPath)
public class RestViewController {

    static final String webContextPath = "/users";

    @Inject
    private UserService userService;

    /**
     * Schickt eine Hello-Nachricht zum Aufrufer zurück.
     *
     * @param name der Name, der in der Hallo-Nachricht angegeben wird
     * @return eine Hallo-Nachricht
     */
    @GET
    @Path("/hello/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloPath(@PathParam("name") String name) {
        return String.format("Hello %s", name);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getById(@PathParam("id") Long id) {
        return userService.findUser(id);
    }

    @PUT
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN})
    public String postUser(UserDto userDto) throws Exception {
        final User user = new User(userDto.getFirstname(), userDto.getLastname());
        final Profile profile = new Profile(userDto.getUsername(), userDto.getPassword());

        // TODO hierfür benötigen wir einen Mapper
        user.setProfile(profile);
        userService.saveUser(user);

        return "Ok";
    }

}
