package com.revature.auction.controllers;

import com.revature.auction.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController
{
    public ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService)
    {
        this.itemService = itemService;
    }
}
