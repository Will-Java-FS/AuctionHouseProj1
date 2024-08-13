package com.revature.auction.controller;

import com.revature.auction.controllers.UserController;
import com.revature.auction.models.User;
import com.revature.auction.services.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImp userService;

    private User testUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        testUser = new User("testUser", "password", false); // Mock user for testing
    }

    @Test
    public void testFindByUsernameAndPassword_UserFound() {
        when(userService.login("testUser", "password")).thenReturn(testUser);

        ResponseEntity<User> response = userController.findByUsernameAndPassword("testUser", "password");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testFindByUsernameAndPassword_UserNotFound() {
        when(userService.login("wrongUser", "wrongPassword")).thenReturn(null);

        ResponseEntity<User> response = userController.findByUsernameAndPassword("wrongUser", "wrongPassword");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteUserById() {
        doNothing().when(userService).deleteAccount(1);

        HttpStatus response = userController.deleteUserById(1);

        assertEquals(HttpStatus.OK, response);
        verify(userService, times(1)).deleteAccount(1);
    }

    @Test
    public void testCreateUser_Success() {
        when(userService.createAccount(testUser)).thenReturn(testUser);

        ResponseEntity<User> response = userController.createUser(testUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testCreateUser_BadRequest() {
        ResponseEntity<User> response = userController.createUser(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testFindUserById() {
        when(userService.findUser(1)).thenReturn(testUser);

        User response = userController.findUserById(1);

        assertEquals(testUser, response);
    }

    @Test
    public void testUpdateUser() {
        User updatedUser = new User("updatedUser", "newPassword", false);
        when(userService.updateUser(1, updatedUser)).thenReturn(updatedUser);

        User response = userController.updateUser(1, updatedUser);

        assertEquals(updatedUser, response);
    }

    @Test
    public void testUserIsAdmin() {
        when(userService.findUser(1)).thenReturn(testUser);

        boolean isAdmin = userController.userIsAdmin(1);

        assertFalse(isAdmin);
    }
}
