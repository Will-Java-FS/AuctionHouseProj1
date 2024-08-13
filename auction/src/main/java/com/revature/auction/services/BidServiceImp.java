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
        // Returning the list of all
        return bidRepo.findAll();
    }

    @Override
    public Bid findById(int id)
    {
        // Returning either an object or nothing based on whether or not it was found
        return bidRepo.findById(id).orElse(null);
    }

    @Override
    public Bid update(int id, Bid bid)
    {
        // Validating object exists
        Bid objectExists = bidRepo.findById(id).orElse(null);

        if(objectExists != null)
        {
            // Saving the updates
            bid.setBid_id(objectExists.getBid_id());
            return bidRepo.save(bid);
        }

        return null; // Nothing was found so return null
    }

    @Override
    public Bid add(Bid object)
    {
        // Adding an object to the database
        return bidRepo.save(object);
    }

    @Override
    public int delete(int id)
    {
        Bid bid = bidRepo.findById(id).orElse(null);

        if(bid != null)
        {
            bidRepo.deleteById(id);
            return 1; // success
        }

        return 0; // failure
    }
}
