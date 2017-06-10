package net.ziemers.swxercise.lg.user.service;

import javax.inject.Inject;

import net.ziemers.swxercise.db.dao.user.UserDao;
import net.ziemers.swxercise.db.utils.JpaTestUtils;
import net.ziemers.swxercise.lg.model.user.User;
import net.ziemers.swxercise.lg.testdatabuilder.user.UserDtoTestDataBuilder;
import net.ziemers.swxercise.lg.user.dto.UserDto;

import static org.junit.Assert.*;

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

    private static String USERNAME_TEST = "username_test";
    private static String EXISTING_USERNAME_TEST = "username_profile";

    private static boolean dbInitialized;

    private UserDto userDto;

    private Long userId;

    @Inject
    private UserDao userDao;

    @Inject
    private UserService underTest;

    @Before
    public void setUp() throws Exception {
        if (!dbInitialized) {
            cleanDb();
            initDbWith("UserServiceTestData.xml");
            dbInitialized = true;
        }
    }

    @Test
    public void testCreateUserReturnsSuccess() {

        given()
                .userDto(USERNAME_TEST);

        when()
                .createUser();

        then()
                .assertCreateSuccess();
    }

    @Test
    public void testCreateUserReturnsFail() {

        given()
                .userDto(EXISTING_USERNAME_TEST);

        when()
                .createUser();

        then()
                .assertCreateFail();
    }

    // given

    private UserServiceTest given() {
        return this;
    }

    private UserServiceTest userDto(final String username) {
        userDto = new UserDtoTestDataBuilder()
                .withUsername(USERNAME_TEST)
                .build();
        return this;
    }

    // when

    private UserServiceTest when() {
        return this;
    }

    private UserServiceTest createUser() {
        txBegin();
        userId = underTest.createUser(userDto);
        txCommit();

        return this;
    }

    // then

    private UserServiceTest then() {
        return this;
    }

    private void assertCreateSuccess() {
        // wir suchen den soeben erstellten Benutzer; wenn er existiert, is alles gut
        final User user = userDao.findByUsername(USERNAME_TEST);
        assertNotNull(user);
    }

    private void assertCreateFail() {
        // es darf kein neuer Benutzer mit identischem "username" erstellt worden sein
        assertNull(userId);
    }

}
