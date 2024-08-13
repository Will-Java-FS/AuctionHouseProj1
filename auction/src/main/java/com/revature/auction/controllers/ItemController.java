package com.revature.auction.controllers;

import com.revature.auction.models.*;
import com.revature.auction.services.ItemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(name="/add", consumes = "application/json",produces ="application/json")
    public ResponseEntity<Object> addItem(@RequestBody Item it)
    {
        return new ResponseEntity<>(itemService.add(it), HttpStatus.OK);
    }

    @GetMapping
    public List<Item> getAllItems()
    {
        return itemService.getAll();
    }

    @GetMapping("/{id}")
    public Item getById(@PathVariable int id)
    {
        return itemService.findById(id);
    }

    @PatchMapping(name="/update/{id}", consumes = "application/json",produces ="application/json")
    public ResponseEntity<Item> updateItem(@RequestBody Item it, @PathVariable int id)
    {
        return new ResponseEntity<>(itemService.update(id, it), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public Item deleteById(@PathVariable int id)
    {
        itemService.delete(id);
        return null;
    }


    @GetMapping("/user/{item_id}")
    public User getUser(@PathVariable int item_id)
    {
        return itemService.getUser(item_id);
    }

    @GetMapping("/bids/{item_id}")
    public List<Bid> getBids(@PathVariable int item_id)
    {
        return itemService.getBids(item_id);
    }

    @GetMapping("/comments/{item_id}")
    public List<Comment> getComments(@PathVariable int item_id)
    {
        return itemService.getComments(item_id);
    }

}
