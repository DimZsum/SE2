package net.ziemers.swxercise.ui;

import net.ziemers.swxercise.lg.model.user.Role;
import net.ziemers.swxercise.lg.user.dto.RoleDto;
import net.ziemers.swxercise.lg.user.enums.RightState;
import net.ziemers.swxercise.lg.user.service.RoleService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * REST-Methoden für die Rollen- und Rechteverwaltung.
 */
@ApplicationScoped
@Path(RoleViewController.webContextPath)
public class RoleViewController {

    static final String webContextPath = "/";

    @Inject
    private RoleService roleService;

    /**
     * Liefert alle Rollen-Objekte zurück.
     * <p>
     * Aufruf:
     * GET http://localhost:8080/swxercise/rest/v1/roles
     *
     * @return die Rollen-Objekte, oder ein leeres JSON-Array, falls keine existieren.
     */
    @GET
    @Path("v1/roles")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(RightState.Constants.ADMIN)
    public Collection<Role> getAllRoles() {
        return roleService.findAllRoles();
    }

    /**
     * Erstellt eine neue Rolle.
     *
     * @param dto
     * @return
     */
    @POST
    @Path("v1/role")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(RightState.Constants.ADMIN)
    public RestResponse createRole(RoleDto dto) {
        Long id = roleService.createRole(dto);
        return new RestResponse();
    }

}
