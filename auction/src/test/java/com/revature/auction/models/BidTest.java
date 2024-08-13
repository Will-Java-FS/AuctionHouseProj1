package com.revature.auction.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BidTest {
    private Bid bid;
    private User mockUser;
    private Item mockItem;

    @BeforeEach
    void setUp() {
        // Initialize mock objects
        mockUser = mock(User.class);
        mockItem = mock(Item.class);
        // Initialize Bid with mock objects
        bid = Bid.builder()
                .bidtime(new Timestamp(System.currentTimeMillis()))
                .amount(100)
                .user(mockUser)
                .item(mockItem)
                .build();
    }

    @Test
    void testBidCreation() {
        assertNotNull(bid);
        assertEquals(100, bid.getAmount());
        assertNotNull(bid.getBidtime());
        assertEquals(mockUser, bid.getUser());
        assertEquals(mockItem, bid.getItem());
    }

    @Test
    void testBidEqualitySameObject() {
        assertEquals(bid, bid);
    }

    @Test
    void testBidEqualityDifferentObjectSameValues() {
        Bid anotherBid = Bid.builder()
                .bidtime(bid.getBidtime())
                .amount(bid.getAmount())
                .user(bid.getUser())
                .item(bid.getItem())
                .build();
        assertEquals(bid, anotherBid);
    }

    @Test
    void testBidEqualityDifferentObjectDifferentValues() {
        Bid differentBid = Bid.builder()
                .bidtime(new Timestamp(System.currentTimeMillis()))
                .amount(200) // different amount
                .user(mockUser)
                .item(mockItem)
                .build();
        assertNotEquals(bid, differentBid);
    }

    @Test
    void testBidHashCodeSameValues() {
        Bid anotherBid = Bid.builder()
                .bidtime(bid.getBidtime())
                .amount(bid.getAmount())
                .user(bid.getUser())
                .item(bid.getItem())
                .build();
        assertEquals(bid.hashCode(), anotherBid.hashCode());
    }

    @Test
    void testBidHashCodeDifferentValues() {
        Bid differentBid = Bid.builder()
                .bidtime(new Timestamp(System.currentTimeMillis()))
                .amount(200)
                .user(mockUser)
                .item(mockItem)
                .build();
        assertNotEquals(bid.hashCode(), differentBid.hashCode());
    }
}
