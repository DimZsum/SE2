package net.ziemers.swxercise.lg.user.service;

import net.ziemers.swxercise.db.dao.user.UserDao;
import net.ziemers.swxercise.db.utils.JpaTestUtils;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Testing your Java CDI application with CDI-Unit couldn't be easier.
 * Just specify @RunWith(CdiRunner.class) on your JUnit test class
 * to enable injection directly into the test class.
 *
 * To test classes in isolation we shouldn't be using their dependencies.
 * Instead we should be using a mock. There are many mocking libraries out
 * there, however CDI-Unit has extra support for Mockito @Mock annotations.
 *
 * Quelle: http://jglue.org/cdi-unit-user-guide/
 */
@RunWith(CdiRunner.class)
public class RoleServiceTest extends JpaTestUtils {

    private static boolean dbInitialized;

    @Inject
    private UserDao userDao;

    @Inject
    private UserService underTest;

    @Before
    public void setUp() throws Exception {
        if (!dbInitialized) {
            cleanDb();
            initDbWith("RoleServiceTestData.xml");
            dbInitialized = true;
        }
    }

    @Test
    public void testSomething() {

        given();

        when();

        then();
    }

    // given

    private RoleServiceTest given() {
        return this;
    }

    // when

    private RoleServiceTest when() {
        return this;
    }

    // then

    private RoleServiceTest then() {
        return this;
    }

}
