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
    @PostMapping("/add", consumes = "application/json",produces ="application/json")
    public ResponseEntity<Object> addItem(@RequestBody Item it)
    {
        return itemService.addItem(it);
    }

    @GetMapping
    public List<Item> getAllItems()
    {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getById(@PathVariable int id)
    {
        return itemService.getById(id);
    }

    @PatchMapping("/update/{id}", consumes = "application/json",produces ="application/json")
    public ResponseEntity<Object> updateItem(@RequestBody Item it, @PathVariable id)
    {
        return itemService.update(id, it);
    }

    @DeleteMapping("/delete/{id}")
    public Item deleteById(@PathVariable int id)
    {
        return itemService.deleteById(id);
    }

}
