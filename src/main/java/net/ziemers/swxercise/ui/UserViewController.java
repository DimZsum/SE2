package net.ziemers.swxercise.ui;

import java.util.Collection;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import net.ziemers.swxercise.lg.user.service.SessionContext;
import net.ziemers.swxercise.lg.model.user.User;
import net.ziemers.swxercise.lg.user.dto.UserDto;
import net.ziemers.swxercise.lg.user.service.UserService;
import net.ziemers.swxercise.ui.enums.ResponseState;

/**
 * REST-Methoden für die Benutzerverwaltung.
 */
@ApplicationScoped
@Path(UserViewController.webContextPath)
public class UserViewController {

    static final String webContextPath = "/";

    @Inject
    private UserService userService;

    /**
     * Liefert alle User-Objekte zurück.
     *
     * Aufruf:
     * GET http://localhost:8080/swxercise/rest/v1/users
     *
     * @return die User-Objekte, oder ein leeres JSON-Array, falls keine existieren.
     */
    @GET
    @Path("v1/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> getAllUsers() {
        return userService.findAllUsers();
    }

    /**
     * Liefert das User-Objekt mit der gewünschten Id zurück.
     *
     * Aufruf:
     * GET http://localhost:8080/swxercise/rest/v1/user/42
     *
     * @param id die Id des gewünschten User-Objekts
     * @return das User-Objekt als JSON, oder <code>null</code>, falls keines existiert.
     */
    @GET
    @Path("v1/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getById(@PathParam("id") Long id) {
        return userService.findUser(id);
    }

    /**
     * Erstellt ein neues User-Objekt mit den gewünschten Eigenschaften, welche mittels {@link UserDto} definiert werden.
     *
     * Aufruf:
     * POST http://localhost:8080/swxercise/rest/v1/user
     *
     * @param dto das mittels der als JSON-Objekt übergebenenen Eigenschaften zu füllende {@link UserDto}
     * @return ein {@link ResponseState}-Objekt mit den Ergebnisinformationen des Aufrufs.

     */
    @POST
    @Path("v1/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public RestResponse createUser(UserDto dto) {
        final Long id = userService.createUser(dto);
        if (id != null) {
            return new RestResponse(ResponseState.SUCCESS, String.valueOf(id));
        }
        return new RestResponse(ResponseState.ALREADY_EXISTING);
    }

    /**
     * Aktualisiert das User-Objekt mit der gewünschten Id mit den Eigenschaften,
     * welche mittels {@link UserDto} definiert werden. Der Pfadparameter wird
     * als erstes ge'marshal't, das DTO im Post-Content danach (REST-Konvention).
     *
     * Aufruf:
     * PUT http://localhost:8080/swxercise/rest/v1/user/42
     *
     * @param id die Id des zu aktualisierenden User-Objekts
     * @param dto das mittels der als JSON-Objekt übergebenenen Eigenschaften zu füllende {@link UserDto}
     * @return ein {@link ResponseState}-Objekt mit den Ergebnisinformationen des Aufrufs.
     */
    @PUT
    @Path("v1/user/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public RestResponse updateUser(@PathParam("id") Long id, UserDto dto) {
        // TODO noch zu implementieren
        return new RestResponse(ResponseState.FAILED);
    }

    /**
     * Löscht das User-Objekt mit der gewünschten Id.
     *
     * Aufruf:
     * DELETE http://localhost:8080/swxercise/rest/v1/user/42
     *
     * @param id die Id des gewünschten User-Objekts
     * @return ein {@link ResponseState}-Objekt mit den Ergebnisinformationen des Aufrufs.
     */
    @DELETE
    @Path("v1/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public RestResponse deleteUser(@PathParam("id") Long id) {
        userService.deleteUser(id);
        return new RestResponse();
    }

    /**
     * Löscht das User-Objekt des zurzeit angemeldeten Benutzers.
     *
     * Aufruf:
     * DELETE http://localhost:8080/swxercise/rest/v1/user
     *
     * @return ein {@link ResponseState}-Objekt mit den Ergebnisinformationen des Aufrufs.
     */
    @DELETE
    @Path("v1/user")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse deleteUser() {
        if (userService.deleteUser()) {
            return new RestResponse();
        }
        return new RestResponse(ResponseState.FAILED);
    }

    /**
     * Meldet einen Benutzer durch übergebenen username und password mit einem neuen User-{@link SessionContext} an.
     *
     * Aufruf:
     * POST http://localhost:8080/swxercise/rest/v1/user/login
     *
     * @param dto das mittels der als JSON-Objekt übergebenenen Eigenschaften zu füllende {@link UserDto}
     * @return ein {@link ResponseState}-Objekt mit den Ergebnisinformationen des Aufrufs.
     */
    @POST
    @Path("v1/user/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse loginUser(UserDto dto) {
        if (userService.loginUser(dto)) {
            return new RestResponse();
        }
        return new RestResponse(ResponseState.FAILED);
    }

    /**
     * Meldet den angemeldeten Benutzer von seinem User-{@link SessionContext} ab.
     *
     * Aufruf:
     * POST http://localhost:8080/swxercise/rest/v1/user/logout
     *
     * @return ein {@link ResponseState}-Objekt mit den Ergebnisinformationen des Aufrufs.
     */
    @POST
    @Path("v1/user/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse logoutUser() {
        if (userService.logoutUser()) {
            return new RestResponse();
        }
        return new RestResponse(ResponseState.FAILED);
    }

}
