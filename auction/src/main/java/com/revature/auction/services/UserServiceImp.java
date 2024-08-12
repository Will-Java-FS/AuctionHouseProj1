package com.revature.auction.services;

import com.revature.auction.models.User;
import com.revature.auction.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
