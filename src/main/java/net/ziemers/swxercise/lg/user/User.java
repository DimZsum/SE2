package net.ziemers.swxercise.lg.user;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import net.ziemers.swxercise.db.BaseEntity;

@Entity
public class User extends BaseEntity {

    private String firstname;

    private String lastname;

    @NotNull
    private Profile profile;

    public User() {
        super();
    }

    public User(final String firstname, final String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

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

    @OneToOne(cascade = {CascadeType.ALL})
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
