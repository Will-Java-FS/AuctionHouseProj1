package com.revature.auction.services;

import com.revature.auction.models.User;
import com.revature.auction.repositories.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImpTest {
    private UserServiceImp userService;
    private UserRepo mockUserRepo;

    @BeforeEach
    void setUp() {
        mockUserRepo = Mockito.mock(UserRepo.class);
        userService = new UserServiceImp(mockUserRepo);
    }

    @Test
    void testLoginSuccessful() {
        User mockUser = new User("testUser", "securePassword");

        when(mockUserRepo.findByUsernameAndPassword("testUser", "securePassword")).thenReturn(mockUser);

        User result = userService.login("testUser", "securePassword");

        assertNotNull(result);
        assertEquals(mockUser, result);
        verify(mockUserRepo, times(1)).findByUsernameAndPassword("testUser", "securePassword");
    }

    @Test
    void testLoginUnsuccessful() {
        when(mockUserRepo.findByUsernameAndPassword("testUser", "wrongPassword")).thenReturn(null);

        User result = userService.login("testUser", "wrongPassword");

        assertNull(result);
        verify(mockUserRepo, times(1)).findByUsernameAndPassword("testUser", "wrongPassword");
    }

    @Test
    void testDeleteAccount() {
        int userId = 1;

        userService.deleteAccount(userId);

        verify(mockUserRepo, times(1)).deleteById(userId);
    }

    @Test
    void testCreateAccount() {
        User newUser = new User("newUser", "newPassword");

        when(mockUserRepo.save(newUser)).thenReturn(newUser);

        User result = userService.createAccount(newUser);

        assertNotNull(result);
        assertEquals(newUser, result);
        verify(mockUserRepo, times(1)).save(newUser);
    }

    @Test
    void testFindUserExists() {
        int userId = 1;
        User mockUser = new User("existingUser", "password");

        when(mockUserRepo.findById(userId)).thenReturn(Optional.of(mockUser));

        User result = userService.findUser(userId);

        assertNotNull(result);
        assertEquals(mockUser, result);
        verify(mockUserRepo, times(1)).findById(userId);
    }

    @Test
    void testFindUserDoesNotExist() {
        int userId = 2;

        when(mockUserRepo.findById(userId)).thenReturn(Optional.empty());

        User result = userService.findUser(userId);

        assertNull(result);
        verify(mockUserRepo, times(1)).findById(userId);
    }

    @Test
    void testUpdateUserExists() {
        int userId = 1;
        User existingUser = new User("existingUser", "password");
        User updatedUser = new User("updatedUser", "newPassword");

        when(mockUserRepo.findById(userId)).thenReturn(Optional.of(existingUser));
        when(mockUserRepo.save(updatedUser)).thenReturn(updatedUser);

        User result = userService.updateUser(userId, updatedUser);

        assertNotNull(result);
        assertEquals(updatedUser, result);
        verify(mockUserRepo, times(1)).findById(userId);
        verify(mockUserRepo, times(1)).save(updatedUser);
    }

    @Test
    void testUpdateUserDoesNotExist() {
        int userId = 1;
        User updatedUser = new User("updatedUser", "newPassword");

        when(mockUserRepo.findById(userId)).thenReturn(Optional.empty());

        User result = userService.updateUser(userId, updatedUser);

        assertNull(result);
        verify(mockUserRepo, times(1)).findById(userId);
        verify(mockUserRepo, never()).save(any(User.class)); // Ensure save was never called
    }
}
