package net.ziemers.swxercise.ui;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(RestApplication.webContextPath)
public class RestApplication extends Application {

    static final String webContextPath = "/rest";

}
