package net.ziemers.swxercise.lg.user.service;

import javax.inject.Inject;

import net.ziemers.swxercise.db.utils.JpaTestUtils;
import net.ziemers.swxercise.lg.model.user.User;
import net.ziemers.swxercise.lg.user.dto.UserDto;
import net.ziemers.swxercise.lg.user.service.UserService;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

@RunWith(CdiRunner.class)
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
