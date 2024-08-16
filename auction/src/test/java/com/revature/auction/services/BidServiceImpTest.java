package com.revature.auction.services;

import com.revature.auction.models.Bid;
import com.revature.auction.repositories.BidRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BidServiceImpTest {
    private BidServiceImp bidService;
    private BidRepo mockBidRepo;

    @BeforeEach
    void setUp() {
        mockBidRepo = Mockito.mock(BidRepo.class);
        bidService = new BidServiceImp(mockBidRepo);
    }

    @Test
    void testGetAllBids() {
        List<Bid> mockBids = new ArrayList<>();
        mockBids.add(new Bid());
        mockBids.add(new Bid());

        when(mockBidRepo.findAll()).thenReturn(mockBids);

        List<Bid> result = bidService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(mockBidRepo, times(1)).findAll();
    }

    @Test
    void testFindByIdExists() {
        int bidId = 1;
        Bid mockBid = new Bid();

        when(mockBidRepo.findById(bidId)).thenReturn(Optional.of(mockBid));

        Bid result = bidService.findById(bidId);

        assertNotNull(result);
        assertEquals(mockBid, result);
        verify(mockBidRepo, times(1)).findById(bidId);
    }

    @Test
    void testFindByIdDoesNotExist() {
        int bidId = 2;

        when(mockBidRepo.findById(bidId)).thenReturn(Optional.empty());

        Bid result = bidService.findById(bidId);

        assertNull(result);
        verify(mockBidRepo, times(1)).findById(bidId);
    }

    @Test
    void testUpdateExists() {
        int bidId = 1;
        Bid existingBid = new Bid();
        Bid updatedBid = new Bid();

        when(mockBidRepo.findById(bidId)).thenReturn(Optional.of(existingBid));
        when(mockBidRepo.save(updatedBid)).thenReturn(updatedBid);

        Bid result = bidService.update(bidId, updatedBid);

        assertNotNull(result);
        assertEquals(updatedBid, result);
        verify(mockBidRepo, times(1)).findById(bidId);
        verify(mockBidRepo, times(1)).save(updatedBid);
    }

    @Test
    void testUpdateDoesNotExist() {
        int bidId = 1;
        Bid updatedBid = new Bid();

        when(mockBidRepo.findById(bidId)).thenReturn(Optional.empty());

        Bid result = bidService.update(bidId, updatedBid);

        assertNull(result);
        verify(mockBidRepo, times(1)).findById(bidId);
        verify(mockBidRepo, never()).save(any(Bid.class)); // Ensure save was never called
    }

    @Test
    void testAddBid() {
        Bid newBid = new Bid();

        when(mockBidRepo.save(newBid)).thenReturn(newBid);

        Bid result = bidService.add(newBid);

        assertNotNull(result);
        assertEquals(newBid, result);
        verify(mockBidRepo, times(1)).save(newBid);
    }

    @Test
    void testDeleteExists() {
        int bidId = 1;
        Bid mockBid = new Bid();

        when(mockBidRepo.findById(bidId)).thenReturn(Optional.of(mockBid));

        int result = bidService.delete(bidId);

        assertEquals(1, result);
        verify(mockBidRepo, times(1)).deleteById(bidId);
    }

    @Test
    void testDeleteDoesNotExist() {
        int bidId = 2;

        when(mockBidRepo.findById(bidId)).thenReturn(Optional.empty());

        int result = bidService.delete(bidId);

        assertEquals(0, result);
        verify(mockBidRepo, never()).deleteById(bidId); // Ensure delete was never called
    }
}
