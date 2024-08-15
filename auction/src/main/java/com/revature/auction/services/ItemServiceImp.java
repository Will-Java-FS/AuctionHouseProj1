package com.revature.auction.services;

import com.revature.auction.models.*;
import com.revature.auction.repositories.ItemRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItemServiceImp implements ItemService, CrudService<Item>
{
    ItemRepo itemRepo;

    @Autowired
    public ItemServiceImp(ItemRepo itemRepo)
    {
        this.itemRepo = itemRepo;
    }

    @Override
    public List<Item> getAll()
    {
        return itemRepo.findAll();
    }

    @Override
    public Item findById(int id)
    {
        return itemRepo.findById(id).orElse(null);
    }

    @Override
    public Item update(int id, Item item)
    {
        Item existingItem = itemRepo.findById(id).orElse(null);

        if(existingItem != null)
        {
            return itemRepo.save(item);
        }

        return null;
    }

    @Override
    public Item add(Item object)
    {
        return itemRepo.save(object);
    }

    @Override
    public int delete(int id)
    {
        Item item = itemRepo.findById(id).orElse(null);

        if(item != null)
        {
            itemRepo.deleteById(id);
            return 1; // Successful deletion
        }

        return 0;
    }

    public User getUser(int id)
    {
        return itemRepo.findOwnerByItemId(id);
    }
    public List<Comment> getComments(int id)
    {
        return itemRepo.findCommentsByItemId(id);
    }
    public List<Bid> getBids(int id)
    {
        return itemRepo.findBidsByItemId(id);
    }

}
