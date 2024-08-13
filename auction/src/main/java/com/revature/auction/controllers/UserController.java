package com.revature.auction.controllers;

import com.revature.auction.models.*;
import com.revature.auction.services.UserServiceImp;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    UserServiceImp userService;

    @Autowired
    public UserController(UserServiceImp userService)
    {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<User> findByUsernameAndPassword(@PathParam("username") String username, @PathParam("password") String password)
    {
        User user = userService.login(username, password);

        if(user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") int id)
    {
        userService.deleteAccount(id);
        return HttpStatus.OK;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        if(user != null)
            return new ResponseEntity<>(userService.createAccount(user), HttpStatus.OK);

        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id)
    {
        return userService.findUser(id);
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user)
    {
        return userService.updateUser(id, user);
    }

    @GetMapping("/admin/{id}")
    public boolean userIsAdmin(@PathVariable int id) //Lock behind key? Find out later
    {
        return userService.findUser(id).isAdmin();
    }

    @GetMapping("/items/{user_id}")
    public List<Item> getItems(@PathVariable int user_id)
    {
        return userService.getItems(user_id);
    }

    @GetMapping("/bids/{user_id}")
    public List<Bid> getBids(@PathVariable int user_id)
    {
        return userService.getBids(user_id);
    }

    @GetMapping("/comments/{user_id}")
    public List<Comment> getComments(@PathVariable int user_id)
    {
        return userService.getComments(user_id);
    }
}
