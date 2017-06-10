package net.ziemers.swxercise.lg.testdata.testdatabuilder.user;

import net.ziemers.swxercise.lg.model.user.Profile;
import net.ziemers.swxercise.lg.testdata.testdatabuilder.AbstractTestDataBuilder;

import javax.persistence.EntityManager;

public class ProfileTestDataBuilder extends AbstractTestDataBuilder {

    private String username = "hbloed";

    private String password = "secret";

    private String mailaddress = "hbloed@ziemers.net";

    public ProfileTestDataBuilder(final EntityManager em) {
        super(em);
    }

    @Override
    public Profile build() {
        return new Profile(username, password)
                .withMailaddress(mailaddress);
    }

    public ProfileTestDataBuilder withUsername(final String username) {
        this.username = username;
        return this;
    }

    public ProfileTestDataBuilder withPassword(final String password) {
        this.password = password;
        return this;
    }

    public ProfileTestDataBuilder withMailaddress(final String mailaddress) {
        this.mailaddress = mailaddress;
        return this;
    }

}
