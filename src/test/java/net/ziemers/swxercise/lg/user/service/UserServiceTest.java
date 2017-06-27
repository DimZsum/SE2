package net.ziemers.swxercise.lg.user.service;

import javax.inject.Inject;

import net.ziemers.swxercise.db.dao.user.UserDao;
import net.ziemers.swxercise.db.utils.JpaTestUtils;
import net.ziemers.swxercise.lg.model.user.User;
import net.ziemers.swxercise.lg.testdatabuilder.user.UserDtoTestDataBuilder;
import net.ziemers.swxercise.lg.user.dto.UserDto;

import static org.junit.Assert.*;

import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.InRequestScope;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
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
    private static String EXISTING_PASSWORD_TEST = "secret";
    private static Long EXISTING_USER_ID = 2L;

    private static boolean dbInitialized;

    private UserDto userDto;

    private boolean actual;

    private User actualUser;

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

    /*
     * In order to inject @SessionScoped beans, one has to annotate the function or class with @InRequestScope
     * as only this annotation guarantees to have the session scope active always.
     */
    @Test
    @InRequestScope
    public void testLoginUserReturnsSuccess() {

        given()
                .userDto(EXISTING_USERNAME_TEST);

        when()
                .loginUser(EXISTING_PASSWORD_TEST);

        then()
                .assertLoginSuccess();
    }

    @Test
    public void testLogoutUserSuccess() {
        // TODO Test ist noch zu implementieren
    }

    @Test
    public void testFindUserById() {

        when()
                .findUser(EXISTING_USER_ID);

        then()
                .assertFindUserByIdSuccess(EXISTING_USER_ID);
    }

    @Test
    @InRequestScope
    public void testFindUser() {

        given()
                .userDto(EXISTING_USERNAME_TEST)
                .loginUser(EXISTING_PASSWORD_TEST);

        when()
                .findUser();

        then()
                .assertFindUserByIdSuccess(EXISTING_USER_ID);
    }

    @Test
    public void testFindAllUsers() {
        // TODO Test ist noch zu implementieren
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
    public void testCreateUserReturnsFailure() {

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

    @Test
    public void testDeleteUserByIdReturnsSuccess() {
        // TODO Test ist noch zu implementieren
    }

    @Test
    public void testDeleteUserReturnsSuccess() {
        // TODO Test ist noch zu implementieren
    }

    @Test
    public void testIsUserAllowedReturnsSuccess() {
        // TODO Test ist noch zu implementieren
    }

    @Test
    public void testIsUserAllowedReturnsFailure() {
        // TODO Test ist noch zu implementieren
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

    private UserServiceTest loginUser(final String password) {
        userDto.withPassword(password);
        actual = underTest.loginUser(userDto);
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

    private UserServiceTest findUser(final Long id) {
        actualUser = underTest.findUser(id);
        return this;
    }

    private UserServiceTest findUser() {
        actualUser = underTest.findUser();
        return this;
    }

    // then

    private UserServiceTest then() {
        return this;
    }

    private void assertLoginSuccess() {
        assertTrue(actual);
    }

    private void assertFindUserByIdSuccess(final Long expectedId) {
        assertEquals(expectedId, actualUser.getId());
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
