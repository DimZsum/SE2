package net.ziemers.swxercise.lg.user;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import net.ziemers.swxercise.db.BaseEntity;
import net.ziemers.swxercise.lg.user.enums.PasswordHashType;

@Entity
public class Profile extends BaseEntity {

    @NotNull
    private String username;

    @NotNull
    private String passwordHash;

    @NotNull
    private PasswordHashType passwordHashType = PasswordHashType.Unknown;

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

    private String getPasswordHash() {
        return passwordHash;
    }

    private void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    private PasswordHashType getPasswordHashType() {
        return passwordHashType;
    }

    private void setPasswordHashType(PasswordHashType passwordHashType) {
        this.passwordHashType = passwordHashType;
    }

    public String getMailaddress() {
        return mailaddress;
    }

    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }

}
