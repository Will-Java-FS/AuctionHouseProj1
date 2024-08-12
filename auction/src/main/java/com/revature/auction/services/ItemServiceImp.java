package com.revature.auction.services;

import com.revature.auction.models.Item;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItemServiceImp implements ItemService, CrudService<Item>
{
    @Override
    public List<Item> getAll()
    {
        return List.of();
    }

    @Override
    public Item findById(int id)
    {
        return null;
    }

    @Override
    public Item update(int id, Item item)
    {
        return null;
    }

    @Override
    public Item add(Item object)
    {
        return null;
    }

    @Override
    public int delete(int id)
    {
        return 0;
    }
}
