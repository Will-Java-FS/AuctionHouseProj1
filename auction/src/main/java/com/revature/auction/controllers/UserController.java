package com.revature.auction.controllers;

import com.revature.auction.models.AuthRequestDTO;
import com.revature.auction.models.JwtResponseDTO;
import com.revature.auction.models.User;
import com.revature.auction.services.JwtService;
import com.revature.auction.services.UserServiceImp;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername())).build(), HttpStatus.OK);
        } else
        {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
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
        String token = jwtService.GenerateToken(savedUser.getUsername());

        // Return the token in the response
        return new ResponseEntity<>(JwtResponseDTO.builder().accessToken(token).build(), HttpStatus.OK);
    }
}
