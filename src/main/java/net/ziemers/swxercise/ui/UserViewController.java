package net.ziemers.swxercise.ui;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import net.ziemers.swxercise.lg.model.user.User;
import net.ziemers.swxercise.lg.user.dto.UserDto;
import net.ziemers.swxercise.lg.user.service.UserService;

@ApplicationScoped
@Path(UserViewController.webContextPath)
public class UserViewController {

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
        userService.saveUser(userDto);
        return "Ok";
    }

}
