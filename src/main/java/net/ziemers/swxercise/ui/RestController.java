package net.ziemers.swxercise.ui;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path(RestController.webContextPath)
public class RestController {

    static final String webContextPath = "/misc";

    private Logger logger;

    @PostConstruct
    public void init() {
        logger = LoggerFactory.getLogger(RestController.class);
    }

    /**
     * Schickt eine Hello-Nachricht zum Aufrufer zurück.
     * 
     * @param name
     *            der Name, der in der Hallo-Nachricht angegeben wird
     * @return eine Hallo-Nachricht
     */
    @GET
    @Path("/hello/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloPath(@PathParam("name") String name) {
        logger.info("Hello {}", name);
        return String.format("Hello %s", name);
    }

}
