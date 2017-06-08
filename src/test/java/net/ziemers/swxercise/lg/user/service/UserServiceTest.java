package net.ziemers.swxercise.lg.user.service;

import javax.inject.Inject;

import net.ziemers.swxercise.db.utils.JpaTestUtils;
import net.ziemers.swxercise.lg.user.dto.UserDto;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/*
 * Testing your Java CDI application with CDI-Unit couldn't be easier.
 * Just specify @RunWith(CdiRunner.class) on your JUnit test class
 * to enable injection directly into the test class.
 *
 * To test classes in isolation we shouldn't be using their dependencies.
 * Instead we should be using a mock. There are many mocking libraries out
 * there, however CDI-Unit has extra support for Mockito @Mock annotations.
 */
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

        underTest.createUser(dto);

        txCommit();
    }

}
