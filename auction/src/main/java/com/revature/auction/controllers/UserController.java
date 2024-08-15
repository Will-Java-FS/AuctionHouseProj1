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
    public ResponseEntity<User> findUserById(@PathVariable int id)
    {
        User ans = userService.findUser(id);
        HttpStatus status = ans != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<User>(ans, status);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user)
    {
        User ans = userService.updateUser(id, user);
        HttpStatus status = ans != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<User>(ans, status);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Boolean> userIsAdmin(@PathVariable int id) //Lock behind key? Find out later
    {
        User user = userService.findUser(id);
        HttpStatus status = user != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<Boolean>(user.isAdmin(), status);
    }

    @GetMapping("/items/{user_id}")
    public ResponseEntity<List<Item>> getItems(@PathVariable int user_id)
    {
        List<Item> ans = userService.getItems(user_id);
        HttpStatus status = ans != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<List<Item>>(ans, status);
    }

    @GetMapping("/bids/{user_id}")
    public ResponseEntity<List<Bid>> getBids(@PathVariable int user_id)
    {
        List<Bid> ans = userService.getBids(user_id);
        HttpStatus status = ans != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<List<Bid>>(ans, status);
    }

    @GetMapping("/comments/{user_id}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable int user_id)
    {
        List<Comment> ans = userService.getComments(user_id);
        HttpStatus status = ans != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<List<Comment>>(ans, status);
    }
}
