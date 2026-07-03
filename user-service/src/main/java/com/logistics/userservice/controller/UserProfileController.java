package com.logistics.userservice.controller;

import com.logistics.userservice.dto.ProfileRequest;
import com.logistics.userservice.dto.ProfileResponse;
import com.logistics.userservice.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService service;

    @PostMapping("/{userId}")
    public ResponseEntity<ProfileResponse> createProfile(
            @PathVariable Long userId,
            @RequestParam String role,
            @Valid @RequestBody ProfileRequest request) {

        ProfileResponse response =
                service.createProfile(userId, role, request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResponse> getProfile(
            @PathVariable Long userId) {

        ProfileResponse response =
                service.getProfile(userId);

        return ResponseEntity.ok(response);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<ProfileResponse> updateProfile(
            @PathVariable Long userId,
            @Valid @RequestBody ProfileRequest request) {

        ProfileResponse response =
                service.updateProfile(userId, request);

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteProfile(
            @PathVariable Long userId) {

        service.deleteProfile(userId);

        return ResponseEntity.noContent().build();

    }

}