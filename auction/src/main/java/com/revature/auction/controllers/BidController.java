package com.revature.auction.controllers;

import com.revature.auction.models.*;
import com.revature.auction.services.BidServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bid")
public class BidController
{
    public BidServiceImp bidService;

    @Autowired
    public BidController(BidServiceImp bidService)
    {
        this.bidService = bidService;
    }

    @GetMapping
    public ResponseEntity<List<Bid>> getAllBids()
    {
        return new ResponseEntity<>(bidService.getAll(), HttpStatus.OK); // returns all bids in the database
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bid> getBidById(@PathVariable("id") int id)
    {
        Bid bid = bidService.findById(id);

        if(bid != null)
            return new ResponseEntity<>(bid, HttpStatus.OK); // object successfully returned

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); // No object with that id
    }

    @PostMapping
    public ResponseEntity<Bid> addBid(@RequestBody Bid bid)
    {
        // Null check
        if(bid == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(bidService.add(bid), HttpStatus.OK); // Object successfully added to database
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Bid> updateBid(@PathVariable("id") int id, @RequestBody Bid bid)
    {
        Bid updatedBid = bidService.update(id, bid);
        if(updatedBid != null)
            return new ResponseEntity<>(updatedBid, HttpStatus.OK); // Successfully updated an item in the database

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // failed to update for some reason
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteById(@PathVariable("id") int id)
    {
        bidService.delete(id); // Attempts to delete a bid
        return HttpStatus.OK; // Deletion was called and 200 is returned regardless of success
    }

    @GetMapping("/user/{bid_id}")
    public ResponseEntity<User> getUser(@PathVariable int bid_id)
    {
        User ans = bidService.getUser(bid_id);

        HttpStatus status = ans != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<User>(ans, status);
    }

    @GetMapping("/item/{bid_id}")
    public ResponseEntity<Item> getItem(@PathVariable int bid_id)
    {
        Item ans=  bidService.getItem(bid_id);
        HttpStatus status = ans != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<Item>(ans, status);
    }
}
