package com.example.javachessserver.user;

import com.example.javachessserver.game.GameNotFoundException;
import com.example.javachessserver.user.models.*;
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

    @PutMapping
    public void updateUser(@AuthenticationPrincipal User user, @RequestBody UserUpdateDetails updateDetails) {
        // FIXME: is there a better way to do this?
        if (updateDetails.getUsername() != null) {
            user.setUsername(updateDetails.getUsername());
        }
        if (updateDetails.getProfilePic() != null) {
            user.setProfilePic(updateDetails.getProfilePic());
        }
        if (updateDetails.getBio() != null) {
            user.setBio(updateDetails.getBio());
        }
        if (updateDetails.getSettings() != null) {
            user.setSettings(updateDetails.getSettings());
        }
        repo.save(user);
    }

    @DeleteMapping
    public void deleteUser(@AuthenticationPrincipal User user) {
        repo.deleteById(user.getId());
    }

    @PutMapping("/password")
    public void updateUserPassword(@AuthenticationPrincipal User user, @RequestBody PasswordUpdateDetails passwordUpdate) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        encoder.matches(passwordUpdate.getOldPassword(), user.getPassword());
        user.setPassword(passwordUpdate.getNewPassword());
        repo.save(user);
    }

    @PutMapping("/{gameId}")
    public void updateUserGame(@AuthenticationPrincipal User user, @PathVariable("gameId") String gameId, @RequestBody UserGameUpdateDetails updateDetails) {
        for (UserGame userGame : user.getPastGames()) {
            if (userGame.getGameId().equals(gameId)) {
                if (updateDetails.getName() != null) {
                    userGame.setName(updateDetails.getName());
                }
                repo.save(user);
                return;
            }
        }
        throw new GameNotFoundException();
    }

    @GetMapping("/{userId}/profile")
    public UserProfile getUserProfile(@PathVariable(value = "userId") String userId) {
        User otherUser = repo.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new UserProfile(otherUser);
    }

    @PostMapping("/{userId}/request")
    public void requestGame(@AuthenticationPrincipal User user, @PathVariable("userId") String otherUserId) {
        // TODO
    }

    @PostMapping("/{userId}/request-response")
    public void respondToGameRequest(@AuthenticationPrincipal User user, @PathVariable("userId") String otherUserId) {
        // TODO
    }

}
