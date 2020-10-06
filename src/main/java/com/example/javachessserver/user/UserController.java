package com.example.javachessserver.user;

import com.example.javachessserver.game.GameNotFoundException;
import com.example.javachessserver.user.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @PutMapping
    public void updateUser(@AuthenticationPrincipal User user, @RequestBody User updatedUser) {
        if (updatedUser.get_id().equals(user.get_id())) {
            // only allow updating of the following fields
            user.setUsername(updatedUser.getUsername());
            user.setBio(updatedUser.getBio());
            user.setProfilePic(updatedUser.getProfilePic());
            user.setSettings(updatedUser.getSettings());
            userRepo.save(user);
        } else
            throw new WrongUserException();
    }

    @DeleteMapping
    public void deleteUser(@AuthenticationPrincipal User user) {
        userRepo.deleteById(user.get_id());
    }

    @PutMapping("/password")
    public void updateUserPassword(@AuthenticationPrincipal User user, @RequestBody UpdatePasswordDetails passwordUpdate) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(passwordUpdate.getOldPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(passwordUpdate.getNewPassword()));
            userRepo.save(user);
        } else {
            throw new InvalidPasswordException();
        }
    }

    @PutMapping("/game/{gameId}")
    public void updateUserGame(@AuthenticationPrincipal User user, @PathVariable("gameId") String gameId, @RequestBody UserGameUpdateDetails updateDetails) {
        for (UserGame userGame : user.getPastGames()) {
            if (userGame.getGameId().equals(gameId)) {
                if (updateDetails.getName() != null) {
                    userGame.setName(updateDetails.getName());
                }
                userRepo.save(user);
                return;
            }
        }
        throw new GameNotFoundException();
    }

    @GetMapping("/{userId}/profile")
    public UserProfile getUserProfile(@PathVariable("userId") String userId) {
        User otherUser = userRepo.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new UserProfile(otherUser);
    }

    @GetMapping("/search")
    public UserProfile searchByUsername(@RequestParam("username") String username) {
        List<User> users = userRepo.findByUsername(username);
        if (!users.isEmpty()) {
            return new UserProfile(users.get(0));
        }
        return null;
    }
}
