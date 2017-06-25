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
    private static Long EXISTING_USER_ID = 2L;

    private static boolean dbInitialized;

    private UserDto userDto;

    private boolean actual;

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
                .assertCreateFailure();
    }

    @Test
    public void testUpdateUserDoesntUpdateUsername() {

        given()
                .userDto(USERNAME_TEST);

        when()
                .updateUser(EXISTING_USER_ID);

        then()
                .assertUpdateSuccess();
    }

    // given

    private UserServiceTest given() {
        return this;
    }

    private UserServiceTest userDto(final String username) {
        userDto = new UserDtoTestDataBuilder()
                .withUsername(username)
                .build();
        return this;
    }

    // when

    private UserServiceTest when() {
        return this;
    }

    private UserServiceTest createUser() {
        txBegin();
        actual = underTest.createUser(userDto);
        txCommit();

        return this;
    }

    private UserServiceTest updateUser(final Long id) {
        txBegin();
        actual = underTest.updateUser(id, userDto);
        txCommit();

        return this;
    }

    // then

    private UserServiceTest then() {
        return this;
    }

    private void assertCreateSuccess() {
        // wir suchen den soeben erstellten Benutzer; wenn er existiert, ist alles gut
        final User user = userDao.findByUsername(USERNAME_TEST);
        assertNotNull(user);
    }

    private void assertCreateFailure() {
        // es darf kein neuer Benutzer mit identischem Benutzernamen erstellt worden sein
        assertFalse(actual);
    }

    private void assertUpdateSuccess() {
        // wir suchen den soeben aktualisierten Benutzer; wenn sein Benutzername unverändert ist, ist alles gut
        final User user = userDao.findById(EXISTING_USER_ID);
        assertEquals(EXISTING_USERNAME_TEST, user.getProfile().getUsername());
    }

}
