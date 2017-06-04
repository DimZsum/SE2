package net.ziemers.swxercise.lg.user;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import net.ziemers.swxercise.db.BaseEntity;

@Entity
public class Profile extends BaseEntity {

    @NotNull
    private String username;

    @NotNull
    private String passwordHash;

    private String mailaddress;

    public Profile() {
        super();
    }

    public Profile(final String username, final String password) {
        this.username = username;
        // TODO Kennwort verhashen
        this.passwordHash = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getMailaddress() {
        return mailaddress;
    }

    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }

}
