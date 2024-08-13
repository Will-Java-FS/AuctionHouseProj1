package com.revature.auction.services;

import com.revature.auction.models.User;

public interface UserService
{
    public User login(String username, String password);
    public void deleteAccount(int id);
    public User createAccount(User user);
    public User findUser(int id);
    public User updateUser(int id, User user);
}
