package com.revature.auction.controllers;

import com.revature.auction.models.*;
import com.revature.auction.services.JwtService;

import com.revature.auction.services.UserServiceImp;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    UserServiceImp userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserServiceImp userService)
    {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO)
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated())
        {
            return new ResponseEntity<>(JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername(), userService.getUserByUsername(authRequestDTO.getUsername()).getUser_id())).build(), HttpStatus.OK);
        } else
        {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @GetMapping
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") int id)
    {
        userService.deleteAccount(id);
        return HttpStatus.OK;
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponseDTO> createUser(@RequestBody User user)
    {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        User savedUser = userService.createAccount(user);

        // Generate a token for the saved user
        String token = jwtService.GenerateToken(savedUser.getUsername(), savedUser.getUser_id());

        // Return the token in the response
        return new ResponseEntity<>(JwtResponseDTO.builder().accessToken(token).build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id)
    {
        User ans = userService.findUser(id);
        HttpStatus status = ans != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<User>(ans, status);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JwtResponseDTO> updateUser(@PathVariable int id, @RequestBody User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User ans = userService.updateUser(id, user);

        HttpStatus status = ans != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        String token = jwtService.GenerateToken(ans.getUsername(), ans.getUser_id());
        return new ResponseEntity<>(JwtResponseDTO.builder().accessToken(token).build(), status);
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
