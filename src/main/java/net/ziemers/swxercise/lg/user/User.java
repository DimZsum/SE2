package net.ziemers.swxercise.lg.user;

import javax.persistence.Entity;

import net.ziemers.swxercise.db.BaseEntity;

@Entity
public class User extends BaseEntity {

    private String firstname;

    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
