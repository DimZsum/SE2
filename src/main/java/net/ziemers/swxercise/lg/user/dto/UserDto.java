package net.ziemers.swxercise.lg.user.dto;

import javax.validation.constraints.NotNull;

/**
 * Das Data Transfer Object im Kontext der Benutzerverwaltung. Es wird unter anderem auch aus einem
 * JSON-Objekt des {@link net.ziemers.swxercise.ui.UserViewController}s gefüllt.
 */
public class UserDto {

    private Long entityId;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String firstname;

    private String lastname;

    private String mailaddress;

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getUsername() {
        return username;
    }

    public UserDto withUsername(final String username) {
        this.username = username;
        return this;
    }

    public String getPassword() { return password; }

    public UserDto withPassword(final String password) {
        this.password = password;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public UserDto withFirstname(final String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserDto withLastname(final String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getMailaddress() {
        return mailaddress;
    }

    public UserDto withMailaddress(final String mailaddress) {
        this.mailaddress = mailaddress;
        return this;
    }

}
