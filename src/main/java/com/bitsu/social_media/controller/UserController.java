package com.bitsu.social_media.controller;


import com.bitsu.social_media.dto.UserBioInfo;
import com.bitsu.social_media.dto.UserPIInfo;
import com.bitsu.social_media.dto.UserProfilePic;
import com.bitsu.social_media.dto.UserProfileResponse;
import com.bitsu.social_media.dto.UserResponse;
import com.bitsu.social_media.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(
            @RequestParam(required = false) String search
    ) {
        return ResponseEntity.ok(userService.getUsers(search));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserProfileResponse> getUser(
            @NotBlank @PathVariable String username
    ) {
        var user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("following")
    public ResponseEntity<List<UserResponse>> getFollowing(
            @RequestParam(required = false) String search
    ) {
        return ResponseEntity.ok(userService.getFollowing(search));
    }

    @GetMapping("followers")
    public ResponseEntity<List<UserResponse>> getFollowers(
            @RequestParam(required = false) String search
    ) {
        return ResponseEntity.ok(userService.getFollowers(search));
    }

    @PutMapping("/PI")
    public void updatePI(
            @Valid @RequestBody UserPIInfo userPIInfo
    ) {
        log.error("Update: " + userPIInfo);
        userService.updatePI (userPIInfo);
        ResponseEntity.ok();
    }

    @PutMapping("/bio")
    public void updateBio(
            @Valid @RequestBody UserBioInfo userBioInfo
    ) {
        log.error("Update: " + userBioInfo);
        userService.updateBio(userBioInfo);
        ResponseEntity.ok();
    }

    @PutMapping("/profilePic")
    public void updateProfilePic(
            @Valid @RequestBody UserProfilePic userProfilePic
    ) {
        log.error("Update: " + userProfilePic);
        userService.updateProfilePic(userProfilePic);
        ResponseEntity.ok();
    }

    @DeleteMapping("/following/{id}")
    public void unfollow(@PathVariable int id) {
        userService.unfollow(id);
        ResponseEntity.ok();
    }

    @PostMapping("/following/{id}")
    public void follow(@PathVariable int id) {
        userService.follow(id);
        ResponseEntity.ok();
    }
}
