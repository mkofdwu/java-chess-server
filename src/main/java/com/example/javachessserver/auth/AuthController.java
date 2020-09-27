package com.example.javachessserver.auth;

import com.example.javachessserver.auth.models.TokenAuthResponse;
import com.example.javachessserver.auth.models.UserLoginDetails;
import com.example.javachessserver.auth.models.UserRegisterDetails;
import com.example.javachessserver.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public User getUser(@AuthenticationPrincipal User user) {
        // returns the entire user object
        return user;
    }

    @PostMapping("/login")
    public TokenAuthResponse login(@RequestBody UserLoginDetails loginDetails) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDetails.getUsername(), loginDetails.getPassword()));
            User user = (User) userDetailsService.loadUserByUsername(loginDetails.getUsername());
            String token = jwtUtil.generateToken(user.getUsername());
            return new TokenAuthResponse(token, user);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid credentials");
        }
    }

    @PostMapping("/register")
    public TokenAuthResponse register(@RequestBody UserRegisterDetails registerDetails) {
        User user = userDetailsService.createUser(registerDetails);
        String token = jwtUtil.generateToken(user.getUsername());
        return new TokenAuthResponse(token, user);
    }
}
