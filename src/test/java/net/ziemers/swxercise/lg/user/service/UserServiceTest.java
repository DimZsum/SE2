package net.ziemers.swxercise.lg.user.service;

import net.ziemers.swxercise.db.utils.JpaTestUtils;
import net.ziemers.swxercise.lg.model.user.User;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest extends JpaTestUtils {

    private static boolean dbInitialized;

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
        getEm().persist(new User("Hein", "Blöd"));
        txCommit();
    }

}
