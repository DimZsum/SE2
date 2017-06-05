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
     * Liefert alle User-Objekte zurück.
     *
     * @return die User-Objekte.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> getAllUsers() {
        return userService.findAllUsers();
    }

    /**
     * Liefert das User-Objekt mit der gewünschten Id zurück.
     *
     * Aufruf:
     * GET http://localhost:8080/swxercise/rest/users/42
     *
     * @param id die Id des gewünschten User-Objekts
     * @return das User-Objekt.
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getById(@PathParam("id") Long id) {
        return userService.findUser(id);
    }

    /**
     * Erstellt ein neues User-Objekt mit den gewünschten Eigenschaften, welche mittels {@link UserDto} definiert werden.
     *
     * Aufruf:
     * POST http://localhost:8080/swxercise/rest/users
     *
     * @param dto das mittels der als JSON-Objekt übergebenenen Eigenschaften zu füllende {@link UserDto}
     * @return "Ok", wenn die Erstellung des User-Objekts erfolgreich war.
     * @throws Exception wirft eine Exception, wenn das Erstellen des UserDto fehlschlug
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createUser(UserDto dto) throws Exception {
        final Long id = userService.createUser(dto);
        return String.format("Ok (#%d)", id);
    }

    /**
     * Aktualisiert das User-Objekt mit der gewünschten Id mit den Eigenschaften, welche mittels {@link UserDto} definiert werden.
     *
     * Aufruf:
     * PUT http://localhost:8080/swxercise/rest/users/42
     *
     * @param id die Id des gewünschten User-Objekts
     * @return "Ok", wenn die Erstellung des User-Objekts erfolgreich war.
     * @throws Exception wirft eine Exception, wenn das Erstellen des UserDto fehlschlug
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateUser(@PathParam("id") Long id) throws Exception {
        // TODO noch zu implementieren
        return "Ok";
    }

    /**
     * Löscht das User-Objekt mit der gewünschten Id.
     *
     * @param id die Id des gewünschten User-Objekts
     * @return "Ok", wenn die Erstellung des User-Objekts erfolgreich war.
     */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteUser(@PathParam("id") Long id) {
        userService.deleteUser(id);
        return "Ok";
    }

    /**
     * Meldet einen Benutzer durch übergebenen username und password mit einer neuen User-{@link net.ziemers.swxercise.lg.model.user.Session} an.
     *
     * @param dto das mittels der als JSON-Objekt übergebenenen Eigenschaften zu füllende {@link UserDto}
     * @return "Ok", wenn die Erstellung des User-Objekts erfolgreich war.
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String loginUser(UserDto dto) {
        if (userService.loginUser(dto)) {
            final User user = userService.getSessionUser();
            return String.format("Ok (%s %s)", user.getFirstname(), user.getLastname());
        }
        return "Failed";
    }

    /**
     * Meldet den angemeldeten Benutzer von seiner User-{@link net.ziemers.swxercise.lg.model.user.Session} ab.
     *
     * @return "Ok", wenn die Erstellung des User-Objekts erfolgreich war.
     */
    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String logoutUser() {
        if (userService.logoutUser()) {
            return "Ok";
        }
        return "Failed";
    }

}
