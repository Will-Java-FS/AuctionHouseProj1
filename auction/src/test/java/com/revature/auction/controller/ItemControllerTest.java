package com.revature.auction.controller;

import com.revature.auction.controllers.ItemController;
import com.revature.auction.models.Item;
import com.revature.auction.models.User;
import com.revature.auction.services.ItemServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemServiceImp itemService;

    private Item testItem;

    private User testUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        testItem = new Item(testUser, "Description"); // Mock item for testing
    }

    @Test
    public void testAddItem() {
        when(itemService.add(testItem)).thenReturn(testItem);

        ResponseEntity<Object> response = itemController.addItem(testItem);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testItem, response.getBody());
    }

    @Test
    public void testGetAllItems() {
        List<Item> items = Arrays.asList(testItem);
        when(itemService.getAll()).thenReturn(items);

        List<Item> response = itemController.getAllItems();

        assertEquals(items.size(), response.size());
        assertEquals(items, response);
    }

    @Test
    public void testGetById() {
        when(itemService.findById(1)).thenReturn(testItem);

        Item response = itemController.getById(1);

        assertEquals(testItem, response);
    }

    @Test
    public void testUpdateItem() {
        Item updatedItem = new Item( testUser,"New Description");
        when(itemService.update(1, updatedItem)).thenReturn(updatedItem);

        ResponseEntity<Item> response = itemController.updateItem(updatedItem, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedItem, response.getBody());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(itemService).delete(1);

        Item response = itemController.deleteById(1);

        assertNull(response);
        verify(itemService, times(1)).delete(1);
    }
}