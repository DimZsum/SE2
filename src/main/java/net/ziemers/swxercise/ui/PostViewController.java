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

import net.ziemers.swxercise.lg.model.user.Post;
import net.ziemers.swxercise.lg.user.dto.PostDto;
import net.ziemers.swxercise.lg.user.enums.RightState;
import net.ziemers.swxercise.lg.user.service.PostService;
import net.ziemers.swxercise.ui.enums.ResponseState;
import net.ziemers.swxercise.ui.utils.RestResponse;

@ApplicationScoped
@Path(UserViewController.webContextPath)

public class PostViewController {
	static final String webContextPath = "/";
	
	@Inject
    private PostService postService;

	@POST
    @Path("v1/post/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(RightState.Constants.ADMIN)
    public RestResponse createPost(PostDto dto) {
        final Long id = postService.createPost(dto);
        if (id != null) {
            return new RestResponse(ResponseState.SUCCESS, String.valueOf(id));
        }
        return new RestResponse(ResponseState.ALREADY_EXISTING);
    }
    
    @GET
    @Path("v1/post/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(RightState.Constants.ADMIN)
    public Post findPost(@PathParam("id") long id) {
        return postService.findPostById(id);
    }

}