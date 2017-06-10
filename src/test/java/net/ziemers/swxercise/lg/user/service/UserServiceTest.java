package net.ziemers.swxercise.lg.user.service;

import javax.inject.Inject;

import net.ziemers.swxercise.db.dao.user.UserDao;
import net.ziemers.swxercise.db.utils.JpaTestUtils;
import net.ziemers.swxercise.lg.model.user.User;
import net.ziemers.swxercise.lg.testdata.testdatabuilder.user.UserDtoTestDataBuilder;
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

    private static boolean dbInitialized;

    private UserDto userDto;

    @Inject
    private UserDao userDao;

    @Inject
    private UserService underTest;

    @Before
    public void setUp() throws Exception {
        if (!dbInitialized) {
            cleanDb();
            //initDbWith("UserService.xml");
            dbInitialized = true;
        }
    }

    @Test
    public void testCreateUserReturnsSuccess() {

        given()
                .userDto();

        when()
                .createUser();

        then()
                .assertCreateSuccess();
    }

    // given

    private UserServiceTest given() {
        return this;
    }

    private UserServiceTest userDto() {
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
        underTest.createUser(userDto);
        txCommit();

        return this;
    }

    // then

    private UserServiceTest then() {
        return this;
    }

    private void assertCreateSuccess() {
        final User user = userDao.findByUsername(USERNAME_TEST);
        assertNotNull(user);
    }

}
