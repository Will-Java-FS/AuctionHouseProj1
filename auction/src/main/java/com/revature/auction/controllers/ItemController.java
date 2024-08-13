package com.revature.auction.controllers;

import com.revature.auction.models.Item;
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

}
