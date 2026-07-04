package com.logistics.userservice.service;

import java.util.UUID;

import com.logistics.userservice.dto.ProfileRequest;
import com.logistics.userservice.dto.ProfileResponse;

public interface UserProfileService {

    ProfileResponse createProfile(UUID userId,
                                  String role,
                                  ProfileRequest request);

    ProfileResponse getProfile(UUID userId);

    ProfileResponse updateProfile(UUID userId,
                                  ProfileRequest request);

    void deleteProfile(UUID userId);

}