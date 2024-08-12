package com.revature.auction.services;

import com.revature.auction.models.User;

public interface UserService
{
    User login(String username, String password);
    void deleteAccount(int id);
    User createAccount(User user);
}
