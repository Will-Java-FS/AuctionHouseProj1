package com.revature.auction.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        // Initialize User object with test data
        user = new User("testUser", "securePassword", false);
    }

    @Test
    void testUserCreation() {
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
        assertEquals("securePassword", user.getPassword());
        assertFalse(user.isAdmin()); // Default value
        assertNull(user.getUserImage()); // Default value is null
    }

    @Test
    void testUserEqualitySameObject() {
        assertEquals(user, user);
    }

    @Test
    void testUserEqualityDifferentObjectSameValues() {
        User anotherUser = new User("testUser", "securePassword", false);
        assertEquals(user, anotherUser);
    }

    @Test
    void testUserEqualityDifferentObjectDifferentValues() {
        User differentUser = new User("differentUser", "anotherPassword", true);
        assertNotEquals(user, differentUser);
    }

    @Test
    void testUserHashCodeSameValues() {
        User anotherUser = new User("testUser", "securePassword", false);
        assertEquals(user.hashCode(), anotherUser.hashCode());
    }

    @Test
    void testUserHashCodeDifferentValues() {
        User differentUser = new User("differentUser", "anotherPassword", true);
        assertNotEquals(user.hashCode(), differentUser.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "User{" +
                "user_id=0, " + // ID is 0 because it's not set yet
                "username='testUser', " +
                "password='securePassword', " +
                "isAdmin=false, " +
                "userImage='null'" + // userImage defaults to null
                '}';
        assertEquals(expectedString, user.toString());
    }

    @Test
    void testSetAdmin() {
        user.setAdmin(true);
        assertTrue(user.isAdmin());
    }

    @Test
    void testSetUserImage() {
        user.setUserImage("path/to/image.png");
        assertEquals("path/to/image.png", user.getUserImage());
    }
}