package net.ziemers.swxercise.lg.user.dto;

import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String firstname;

    private String lastname;

    private String mailaddress;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMailaddress() {
        return mailaddress;
    }

}
