package com.revature.auction.controllers;

import com.revature.auction.services.ItemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController
{
    public ItemServiceImp itemService;

    @Autowired
    public ItemController(ItemServiceImp itemService)
    {
        this.itemService = itemService;
    }
}
