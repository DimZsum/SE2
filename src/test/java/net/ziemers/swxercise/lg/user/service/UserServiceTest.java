package net.ziemers.swxercise.lg.user.service;

import net.ziemers.swxercise.db.utils.JpaTestUtils;
import org.junit.Test;

public class UserServiceTest extends JpaTestUtils {

    @Test
    public void testService() {
        txBegin();
        txCommit();
    }

}
