package com.revature.auction.services;

import com.revature.auction.models.Item;
import com.revature.auction.repositories.ItemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceImpTest {
    private ItemServiceImp itemService;
    private ItemRepo mockItemRepo;

    @BeforeEach
    void setUp() {
        mockItemRepo = Mockito.mock(ItemRepo.class);
        itemService = new ItemServiceImp(mockItemRepo);
    }

    @Test
    void testGetAllItems() {
        List<Item> mockItems = new ArrayList<>();
        mockItems.add(new Item());
        mockItems.add(new Item());

        when(mockItemRepo.findAll()).thenReturn(mockItems);

        List<Item> result = itemService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(mockItemRepo, times(1)).findAll();
    }

    @Test
    void testFindByIdExists() {
        int itemId = 1;
        Item mockItem = new Item();

        when(mockItemRepo.findById(itemId)).thenReturn(Optional.of(mockItem));

        Item result = itemService.findById(itemId);

        assertNotNull(result);
        assertEquals(mockItem, result);
        verify(mockItemRepo, times(1)).findById(itemId);
    }

    @Test
    void testFindByIdDoesNotExist() {
        int itemId = 2;

        when(mockItemRepo.findById(itemId)).thenReturn(Optional.empty());

        Item result = itemService.findById(itemId);

        assertNull(result);
        verify(mockItemRepo, times(1)).findById(itemId);
    }

    @Test
    void testUpdateExists() {
        int itemId = 1;
        Item existingItem = new Item();
        Item updatedItem = new Item();

        when(mockItemRepo.findById(itemId)).thenReturn(Optional.of(existingItem));
        when(mockItemRepo.save(updatedItem)).thenReturn(updatedItem);

        Item result = itemService.update(itemId, updatedItem);

        assertNotNull(result);
        assertEquals(updatedItem, result);
        verify(mockItemRepo, times(1)).findById(itemId);
        verify(mockItemRepo, times(1)).save(updatedItem);
    }

    @Test
    void testUpdateDoesNotExist() {
        int itemId = 1;
        Item updatedItem = new Item();

        when(mockItemRepo.findById(itemId)).thenReturn(Optional.empty());

        Item result = itemService.update(itemId, updatedItem);

        assertNull(result);
        verify(mockItemRepo, times(1)).findById(itemId);
        verify(mockItemRepo, never()).save(any(Item.class)); // Ensure save was never called
    }

    @Test
    void testAddItem() {
        Item newItem = new Item();

        when(mockItemRepo.save(newItem)).thenReturn(newItem);

        Item result = itemService.add(newItem);

        assertNotNull(result);
        assertEquals(newItem, result);
        verify(mockItemRepo, times(1)).save(newItem);
    }

    @Test
    void testDeleteExists() {
        int itemId = 1;
        Item mockItem = new Item();

        when(mockItemRepo.findById(itemId)).thenReturn(Optional.of(mockItem));

        int result = itemService.delete(itemId);

        assertEquals(1, result);
        verify(mockItemRepo, times(1)).deleteById(itemId);
    }

    @Test
    void testDeleteDoesNotExist() {
        int itemId = 2;

        when(mockItemRepo.findById(itemId)).thenReturn(Optional.empty());

        int result = itemService.delete(itemId);

        assertEquals(0, result);
        verify(mockItemRepo, never()).deleteById(itemId); // Ensure delete was never called
    }
}
