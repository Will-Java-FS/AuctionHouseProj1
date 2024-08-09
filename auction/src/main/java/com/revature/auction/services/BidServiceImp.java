package com.revature.auction.services;

import com.revature.auction.models.Bid;
import com.revature.auction.repositories.BidRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BidServiceImp implements BidService, CrudService<Bid>
{
    public BidRepo bidRepo;

    @Autowired
    public BidServiceImp(BidRepo bidRepo)
    {
        this.bidRepo = bidRepo;
    }

    @Override
    public List<Bid> getAll()
    {
        return List.of();
    }

    @Override
    public Bid findById(int id)
    {
        return null;
    }

    @Override
    public Bid update(int id)
    {
        return null;
    }

    @Override
    public Bid add(Bid object)
    {
        return null;
    }

    @Override
    public int delete(int id)
    {
        return 0;
    }
}
