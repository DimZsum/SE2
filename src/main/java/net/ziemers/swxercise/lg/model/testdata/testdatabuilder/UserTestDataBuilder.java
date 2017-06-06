package net.ziemers.swxercise.lg.model.testdata.testdatabuilder;

import net.ziemers.swxercise.lg.model.user.Address;
import net.ziemers.swxercise.lg.model.user.Profile;
import net.ziemers.swxercise.lg.model.user.User;

public class UserTestDataBuilder extends AbstractTestDataBuilder<User> {

    private String firstname = "Hein";

    private String lastname = "Bloed";

    private Profile profile;

    private Address address = null;

    public UserTestDataBuilder(final EntityManger em) {
        super(em);
        profile = new ProfileTestDataBuilder(em).build();
    }

    @Override
    public User build() {
        return new User(firstname, lastname)
                .withProfile(profile)
                .withAddress(address);
    }

    public UserTestDataBuilder withFirstname(final String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserTestDataBuilder withLastname(final String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserTestDataBuidler withProfile(final Profile profile) {
        this.profile = profile;
        return this;
    }

    public UserTestDataBuilder withAddress(final Address address) {
        this.address = address;
        return this;
    }

}

