package com.example.javachessserver.user;

import com.example.javachessserver.user.models.PasswordUpdateDetails;
import com.example.javachessserver.user.models.User;
import com.example.javachessserver.user.models.UserProfile;
import com.example.javachessserver.user.models.ProfileUpdateDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/")
    public User getUser(@AuthenticationPrincipal User user) {
        // returns the entire user object
        return user;
    }

    @GetMapping("/{userId}/profile")
    public UserProfile getUserProfile(@PathVariable(value = "userId") String userId) {
        User otherUser = repo.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new UserProfile(otherUser);
    }

    @PutMapping("/")
    public void updateUser(@AuthenticationPrincipal User user, @RequestBody ProfileUpdateDetails profileDetails) {
        // TODO
        repo.deleteById(user.getId());
        repo.insert()
    }

    @PutMapping("/password")
    public void updateUserPassword(@RequestBody PasswordUpdateDetails passwordDetails) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        encoder.
        passwordDetails.getOldPassword()
    }

    @DeleteMapping("/")
    public void deleteUser(@AuthenticationPrincipal User user) {
        repo.deleteById(user.getId());
    }

}
