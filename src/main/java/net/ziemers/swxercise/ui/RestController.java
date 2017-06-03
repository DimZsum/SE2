package net.ziemers.swxercise.ui;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.ziemers.swxercise.lg.user.User;
import net.ziemers.swxercise.lg.user.service.UserService;

@ApplicationScoped
@Path(RestController.webContextPath)
public class RestController {

    static final String webContextPath = "/user";

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
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> getAllUsers() {
        return userService.findAllUsers();
    }

}
