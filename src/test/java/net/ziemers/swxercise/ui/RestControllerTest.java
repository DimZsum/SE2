package net.ziemers.swxercise.ui;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestControllerTest {
    
    @InjectMocks
    private RestController underTest;

    @Test
    public void test() {
        final String name = "Tom";
        final String expected = "Hello " + name;
        String actual;

        actual = underTest.helloPath(name);
        Assert.assertEquals(expected, actual);
    }

}
