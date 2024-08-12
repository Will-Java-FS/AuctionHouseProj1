package com.revature.auction.services;

public interface BidService
{
    public List<Bid> getAll();

    public Bid findById(int id);

    public Bid update(int id);

    public Bid add(Bid object);

    public int delete(int id);
}
