package net.ziemers.swxercise.lg.user.enums;

/**
 * Stellt grundsätzliche Rechte zur Verfügung, die so mit hoher Wahrscheinlichkeit
 * in vielen Anwendungen verwendet werden möchten.
 */
public enum RightState {

    NOT_LOGGED_IN(Constants.NOT_LOGGED_IN),
    LOGGED_IN(Constants.LOGGED_IN),
    ADMIN(Constants.ADMIN),
    SUPERADMIN(Constants.SUPERADMIN),
    ;

    private String name;

    RightState(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    /*
     * Diese Klasse wird verwendet, damit wir innerhalb von Annotationen auf die Namen zugreifen können.
     */
    public static class Constants {
        public static final String NOT_LOGGED_IN = "NOT_LOGGED_IN";
        public static final String LOGGED_IN = "LOGGED_IN";
        public static final String ADMIN = "ADMIN";
        public static final String SUPERADMIN = "SUPERADMIN";
    }

}
