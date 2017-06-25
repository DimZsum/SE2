package net.ziemers.swxercise.ui;

import net.ziemers.swxercise.lg.testdatabuilder.user.UserDtoTestDataBuilder;
import net.ziemers.swxercise.lg.user.dto.UserDto;
import net.ziemers.swxercise.lg.user.service.UserService;
import net.ziemers.swxercise.ui.enums.ResponseState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserViewControllerTest {

    /*
     * You have the @InjectMocks annotation which tries to do constructor,
     * method and field dependency injection based on the type.
     */
    @InjectMocks
    private UserViewController underTest;

    /*
     * Mockito allows to configure the return values of its mocks via a fluent API.
     * Unspecified method calls return "empty" values
     */
    @Mock
    private UserService userService;

    private UserDto userDto;

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

        given()
                .userDto();

        doing()
                .createUser(true);

        then()
                .assertCreateSuccess();
    }

    @Test
    public void testCreateUserReturnsFailure() {

        given()
                .userDto();

        doing()
                .createUser(false);

        then()
                .assertCreateFailure();
    }

    @Test
    public void testUpdateLoggedInUserReturnsSuccess() {

        given()
                .userDto();

        doing()
                .updateUser();

        then()
                .assertUpdateSuccess();
    }

    @Test
    public void testUpdateSpecificUserReturnsSuccess() {

        given()
                .userDto();

        doing()
                .updateUser(1L);

        then()
                .assertUpdateSuccess();
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
                .userDto();

        doing()
                .loginUser(true);

        then()
                .assertLoginSuccess();
    }

    @Test
    public void testLoginNonExistingUserReturnsFailure() {

        given()
                .userDto();

        doing()
                .loginUser(false);

        then()
                .assertLoginFailure();
    }

    @Test
    public void testLogoutUserReturnsSuccess() {

        doing()
                .logoutUser();

        then()
                .assertLogoutSuccess();
    }

    // given

    private UserViewControllerTest given() {
        return this;
    }

    private UserViewControllerTest userDto() {
        userDto = new UserDtoTestDataBuilder().build();
        return this;
    }

    // doing

    private UserViewControllerTest doing() {
        return this;
    }

    private void createUser(final boolean result) {
        /*
         * The when().thenReturn() method chain is used to specify
         * a return value for a method call with pre-defined parameters.
         */
        when(userService.createUser(userDto)).thenReturn(result);

        actual = underTest.createUser(userDto);
    }

    private void updateUser() {
        when(userService.updateUser(userDto)).thenReturn(true);

        actual = underTest.updateUser(userDto);
    }

    private void updateUser(final Long id) {
        when(userService.updateUser(id, userDto)).thenReturn(true);

        actual = underTest.updateUser(id, userDto);
    }

    private void loginUser(final boolean result) {
        when(userService.loginUser(userDto)).thenReturn(result);

        actual = underTest.loginUser(userDto);
    }

    private void logoutUser() {
        when(userService.logoutUser()).thenReturn(true);

        actual = underTest.logoutUser();
    }

    // then

    private UserViewControllerTest then() {
        return this;
    }

    private void assertCreateSuccess() {
        final RestResponse expected = new RestResponse();
        assertEquals(expected, actual);
    }

    private void assertCreateFailure() {
        final RestResponse expected = new RestResponse(ResponseState.ALREADY_EXISTING);
        assertEquals(expected, actual);
    }

    private void assertUpdateSuccess() {
        final RestResponse expected = new RestResponse();
        assertEquals(expected, actual);
    }

    private void assertLoginSuccess() {
        final RestResponse expected = new RestResponse();
        assertEquals(expected, actual);
    }

    private void assertLoginFailure() {
        final RestResponse expected = new RestResponse(ResponseState.FAILED);
        assertEquals(expected, actual);
    }

    private void assertLogoutSuccess() {
        final RestResponse expected = new RestResponse();
        assertEquals(expected, actual);
    }

}
