package com.revature.auction.services;

import com.revature.auction.models.*;
import com.revature.auction.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserServiceImp implements UserService
{
    UserRepo userRepo;

    @Autowired
    public UserServiceImp(UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }

    @Override
    public User login(String username, String password)
    {
        return userRepo.findByUsernameAndPassword(username, password);
    }

    @Override
    public void deleteAccount(int id)
    {
        userRepo.deleteById(id);
    }

    @Override
    public User createAccount(User user)
    {
        return userRepo.save(user);
    }

    @Override
    public User findUser(int id)
    {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User updateUser(int id, User user)
    {
        // Validating object exists
        User objectExists = userRepo.findById(id).orElse(null);

        if(objectExists != null)
        {
            // Saving the updates
            user.setUser_id(objectExists.getUser_id());
            return userRepo.save(user);
        }

        return null; // Nothing was found so return null
    }

    public List<Item> getItems(int id)
    {
        return userRepo.findItemsByUserId(id);
    }

    public List<Bid> getBids(int id)
    {
        return userRepo.findBidsByUserId(id);
    }
    public List<Comment> getComments(int id)
    {
        return userRepo.findCommentsByUserId(id);
    }

    public User getUserByUsername(String username)
    {
        return userRepo.findByUsername(username);
    }
}
