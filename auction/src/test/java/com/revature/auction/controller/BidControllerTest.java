package com.revature.auction.controller;

import com.revature.auction.controllers.BidController;
import com.revature.auction.models.Bid;
import com.revature.auction.services.BidServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BidControllerTest {
    private BidController bidController;
    private BidServiceImp mockBidService;

    @BeforeEach
    void setUp() {
        mockBidService = Mockito.mock(BidServiceImp.class);
        bidController = new BidController(mockBidService);
    }

    @Test
    void testGetAllBids() {
        List<Bid> mockBids = new ArrayList<>();
        mockBids.add(new Bid());
        mockBids.add(new Bid());

        when(mockBidService.getAll()).thenReturn(mockBids);

        ResponseEntity<List<Bid>> response = bidController.getAllBids();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(mockBidService, times(1)).getAll();
    }

    @Test
    void testGetBidByIdExists() {
        int bidId = 1;
        Bid mockBid = new Bid();

        when(mockBidService.findById(bidId)).thenReturn(mockBid);

        ResponseEntity<Bid> response = bidController.getBidById(bidId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockBid, response.getBody());
        verify(mockBidService, times(1)).findById(bidId);
    }

    @Test
    void testGetBidByIdDoesNotExist() {
        int bidId = 2;

        when(mockBidService.findById(bidId)).thenReturn(null);

        ResponseEntity<Bid> response = bidController.getBidById(bidId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(mockBidService, times(1)).findById(bidId);
    }

    @Test
    void testAddBid() {
        Bid newBid = new Bid();

        when(mockBidService.add(newBid)).thenReturn(newBid);

        ResponseEntity<Bid> response = bidController.addBid(newBid);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(newBid, response.getBody());
        verify(mockBidService, times(1)).add(newBid);
    }

    @Test
    void testAddBidNull() {
        ResponseEntity<Bid> response = bidController.addBid(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(mockBidService, never()).add(any(Bid.class)); // Ensure add was never called
    }

    @Test
    void testUpdateBidExists() {
        int bidId = 1;
        Bid existingBid = new Bid();
        Bid updatedBid = new Bid();

        when(mockBidService.update(bidId, updatedBid)).thenReturn(updatedBid);

        ResponseEntity<Bid> response = bidController.updateBid(bidId, updatedBid);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedBid, response.getBody());
        verify(mockBidService, times(1)).update(bidId, updatedBid);
    }

    @Test
    void testUpdateBidDoesNotExist() {
        int bidId = 1;
        Bid updatedBid = new Bid();

        when(mockBidService.update(bidId, updatedBid)).thenReturn(null);

        ResponseEntity<Bid> response = bidController.updateBid(bidId, updatedBid);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(mockBidService, times(1)).update(bidId, updatedBid);
    }

    @Test
    void testDeleteById() {
        int bidId = 1;

        HttpStatus response = bidController.deleteById(bidId);

        assertEquals(HttpStatus.OK, response);
        verify(mockBidService, times(1)).delete(bidId);
    }
}