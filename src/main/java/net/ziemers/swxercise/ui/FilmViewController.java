package net.ziemers.swxercise.ui;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.ziemers.swxercise.lg.model.user.Film;
import net.ziemers.swxercise.lg.model.user.Role;
import net.ziemers.swxercise.lg.user.dto.FilmDto;
import net.ziemers.swxercise.lg.user.enums.RightState;
import net.ziemers.swxercise.lg.user.service.FilmService;
import net.ziemers.swxercise.ui.enums.ResponseState;
import net.ziemers.swxercise.ui.utils.RestResponse;

@ApplicationScoped
@Path(UserViewController.webContextPath)

public class FilmViewController {
	static final String webContextPath = "/";
	
	@Inject
    private FilmService filmService;

	@POST
    @Path("v1/film/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(RightState.Constants.ADMIN)
    public RestResponse createFilm(FilmDto dto) {
        final Long id = filmService.createFilm(dto);
        if (id != null) {
            return new RestResponse(ResponseState.SUCCESS, String.valueOf(id));
        }
        return new RestResponse(ResponseState.ALREADY_EXISTING);
    }
    
    @GET
    @Path("v1/film/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(RightState.Constants.ADMIN)
    public Film findFilm(@PathParam("id") long id) {
        return filmService.findFilmById(id);
    }

}