package com.revature.auction.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemTest {
    private Item item;
    private User mockUser;

    @BeforeEach
    void setUp() {
        // Initialize mock objects
        mockUser = mock(User.class);
        // Initialize Item with mock objects and default properties
        item = new Item(mockUser, "Sample Item");
        item.setItemDescription("This is a sample item description.");
        item.setItemImage("sample_image.png");
    }

    @Test
    void testItemCreation() {
        assertNotNull(item);
        assertEquals(mockUser, item.getOwner());
        assertEquals("Sample Item", item.getItemName());
        assertEquals("This is a sample item description.", item.getItemDescription());
        assertEquals("sample_image.png", item.getItemImage());
        assertFalse(item.isApproved()); // Default value
    }

    @Test
    void testItemEqualitySameObject() {
        assertEquals(item, item);
    }

    @Test
    void testItemEqualityDifferentObjectSameValues() {
        Item anotherItem = new Item(mockUser, "Sample Item");
        anotherItem.setItemDescription(item.getItemDescription());
        anotherItem.setItemImage(item.getItemImage());
        assertEquals(item, anotherItem);
    }

    @Test
    void testItemEqualityDifferentObjectDifferentValues() {
        Item differentItem = new Item(mockUser, "Different Item");
        assertNotEquals(item, differentItem);
    }

    @Test
    void testItemHashCodeSameValues() {
        Item anotherItem = new Item(mockUser, "Sample Item");
        anotherItem.setItemDescription(item.getItemDescription());
        anotherItem.setItemImage(item.getItemImage());
        assertEquals(item.hashCode(), anotherItem.hashCode());
    }

    @Test
    void testItemHashCodeDifferentValues() {
        Item differentItem = new Item(mockUser, "Different Item");
        assertNotEquals(item.hashCode(), differentItem.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "Item{" +
                "item_id=0, " + // ID is 0 because it's not set yet
                "owner=" + mockUser + ", " +
                "itemName='Sample Item', " +
                "itemDescription='This is a sample item description.', " +
                "itemImage='sample_image.png', " +
                "isApproved=false" +
                '}';
        assertEquals(expectedString, item.toString());
    }

    @Test
    void testApprovalStatus() {
        item.setApproved(true);
        assertTrue(item.isApproved());
    }
}