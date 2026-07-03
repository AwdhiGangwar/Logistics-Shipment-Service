package com.logistics.userservice.service;

import com.logistics.userservice.dto.ProfileRequest;
import com.logistics.userservice.dto.ProfileResponse;

public interface UserProfileService {

    ProfileResponse createProfile(Long userId,
                                  String role,
                                  ProfileRequest request);

    ProfileResponse getProfile(Long userId);

    ProfileResponse updateProfile(Long userId,
                                  ProfileRequest request);

    void deleteProfile(Long userId);

}