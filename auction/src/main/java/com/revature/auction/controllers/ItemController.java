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

    @PostMapping()
    public ResponseEntity<Object> addItem(@RequestBody Item it)
    {
        return new ResponseEntity<>(itemService.add(it), it != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
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

    @PatchMapping("/{id}")
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
    public ResponseEntity<User> getUser(@PathVariable int item_id)
    {
        User ans = itemService.getUser(item_id);
        HttpStatus status = ans != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<User>(ans, status);
    }

    @GetMapping("/bids/{item_id}")
    public ResponseEntity<List<Bid>> getBids(@PathVariable int item_id)
    {
        List<Bid> ans = itemService.getBids(item_id);
        HttpStatus status = ans != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<List<Bid>>(ans, status);
    }

    @GetMapping("/comments/{item_id}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable int item_id)
    {
        List<Comment> ans = itemService.getComments(item_id);
        HttpStatus status = ans != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<List<Comment>>(ans, status);
    }

}
