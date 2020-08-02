package com.example.javachessserver.auth;

import com.example.javachessserver.auth.models.TokenAuthResponse;
import com.example.javachessserver.auth.models.UserLoginDetails;
import com.example.javachessserver.auth.models.UserRegisterDetails;
import com.example.javachessserver.user.UserRepository;
import com.example.javachessserver.user.models.User;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/login")
    public TokenAuthResponse login(@RequestBody UserLoginDetails loginDetails) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDetails.getUsername(), loginDetails.getPassword()));
            // TODO: fix this
            BasicDBObject query = new BasicDBObject();
            query.put("username", loginDetails.getUsername());
            User user = userRepo.findOne(query).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
            String token = JwtService.generateToken(user.getId());
            return new TokenAuthResponse(token);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid credentials");
        }
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRegisterDetails registerDetails) {

    }
}
