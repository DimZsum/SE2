package net.ziemers.swxercise.ui;

import net.ziemers.swxercise.lg.model.user.User;
import net.ziemers.swxercise.lg.user.dto.UserDto;
import net.ziemers.swxercise.lg.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserViewControllerTest {

    @InjectMocks
    private UserViewController underTest;

    @Mock
    private UserService userService;

    private UserDto userDto;

    private User user;

    private RestResponse actual;

    @Test
    public void testJUnitFrameworkReturnsTrue() {
        assertTrue(true);
    }

    @Test(expected = AssertionError.class)
    public void testJUnitFrameworkThrowsException() {
        assertTrue(false);
    }

    @Test
    public void testGetAllUsersReturnsUsersJson() {
        // TODO Test ist noch zu implementieren
    }

    @Test
    public void testGetUserByIdReturnsUserJson() {
        // TODO Test ist noch zu implementieren
    }

    @Test
    public void testCreateUserReturnsSuccess() {
        // TODO Test ist noch zu implementieren
    }

    @Test
    public void testUpdateUserReturnsSuccess() {
        // TODO Test ist noch zu implementieren
    }

    @Test
    public void testDeleteUserByIdReturnsSuccess() {
        // TODO Test ist noch zu implementieren
    }

    @Test
    public void testDeleteSessionUserReturnsSuccess() {
        // TODO Test ist noch zu implementieren
    }

    @Test
    public void testLoginUserReturnsSuccess() {

        given()
                .userDto()
                .user();

        doing()
                .loginUser();

        then()
                .assertLoginSuccess();
    }

    @Test
    public void testLogoutUserReturnsSuccess() {
        // TODO Test ist noch zu implementieren
    }

    // given

    private UserViewControllerTest given() {
        return this;
    }

    private UserViewControllerTest userDto() {
        userDto = new UserDto()
                .withUsername("hbloed")
                .withPassword("secret");

        return this;
    }

    private UserViewControllerTest user() {
        user = new User("Hein", "Blöd");
        return this;
    }

    // doing

    private UserViewControllerTest doing() {
        return this;
    }

    private void loginUser() {
        // wir simulieren den Erfolg des userService (der hier nicht getestet werden soll),
        // sofern das korrekte userDto an ihn übergeben wurde
        when(userService.loginUser(userDto)).thenReturn(true);
        //when(userService.getSessionUser()).thenReturn(user);

        actual = underTest.loginUser(userDto);
    }

    // then

    private UserViewControllerTest then() {
        return this;
    }

    private void assertLoginSuccess() {
        final RestResponse expected = new RestResponse();
        assertEquals(expected, actual);
    }

}
