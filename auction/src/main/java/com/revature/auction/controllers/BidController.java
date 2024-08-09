package com.revature.auction.controllers;

import com.revature.auction.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bid")
public class BidController
{
    public BidService bidService;

    @Autowired
    public BidController(BidService bidService)
    {
        this.bidService = bidService;
    }
}
