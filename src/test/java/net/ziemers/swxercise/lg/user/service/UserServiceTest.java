package net.ziemers.swxercise.lg.user.service;

import javax.inject.Inject;

import net.ziemers.swxercise.db.utils.JpaTestUtils;
import net.ziemers.swxercise.lg.user.dto.UserDto;

import org.junit.Before;
import org.junit.Test;

public class UserServiceTest extends JpaTestUtils {

    private static boolean dbInitialized;

    @Inject
    private UserService underTest;

    @Before
    public void setup() throws Exception {
        if (!dbInitialized) {
            cleanDb();
            //initDbWith("UserService.xml");
            dbInitialized = true;
        }
    }

    @Test
    public void testService() {
        txBegin();

        final UserDto dto = new UserDto()
                .withFirstname("Hein")
                .withLastname("Blöd")
                .withUsername("tziemer")
                .withPassword("secret");

        //underTest.createUser(dto);

        txCommit();
    }

}
