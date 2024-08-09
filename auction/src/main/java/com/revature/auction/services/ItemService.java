package com.revature.auction.services;

public interface ItemService
{
    public List<Item> getAll();
    public Item findById(int id);
    public Item update(int id);
    public Item add(Item object);
    public int delete(int id);

}
