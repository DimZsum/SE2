package net.ziemers.swxercise.lg.user;

import javax.persistence.Entity;

import net.ziemers.swxercise.db.BaseEntity;

@Entity
public class Profile extends BaseEntity {

    private String passwordHash;

    private String mailaddress;

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
