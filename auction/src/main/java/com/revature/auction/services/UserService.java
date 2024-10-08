package com.revature.auction.services;

import com.revature.auction.models.*;
import java.util.*;
public interface UserService
{
    public User login(String username, String password);
    public void deleteAccount(int id);
    public User createAccount(User user);
    public User findUser(int id);
    public User updateUser(int id, boolean isAdmin);
    public List<Item> getItems(int uid);
    public List<Bid> getBids(int uid);
    public List<Comment> getComments(int uid);
    public User getUserByUsername(String username);
    public List<User> getAllUsers();
}
