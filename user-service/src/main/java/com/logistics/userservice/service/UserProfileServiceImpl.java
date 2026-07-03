package com.logistics.userservice.service.impl;

import com.logistics.userservice.dto.ProfileRequest;
import com.logistics.userservice.dto.ProfileResponse;
import com.logistics.userservice.entity.UserProfile;
import com.logistics.userservice.exception.ProfileNotFoundException;
import com.logistics.userservice.repository.UserProfileRepository;
import com.logistics.userservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import com.logistics.userservice.exception.ProfileAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;

   @Override
public ProfileResponse createProfile(Long userId,
                                     String role,
                                     ProfileRequest request) {

    if (repository.existsByUserId(userId)) {
        throw new ProfileAlreadyExistsException("Profile already exists.");
    }

    UserProfile profile = UserProfile.builder()
            .userId(userId)
            .role(role)
            .fullName(request.getFullName())
            .phoneNumber(request.getPhoneNumber())
            .address(request.getAddress())
            .build();

    UserProfile savedProfile = repository.save(profile);

    return mapToResponse(savedProfile);
}

    @Override
    public ProfileResponse getProfile(Long userId) {

        UserProfile profile = repository.findByUserId(userId)
                .orElseThrow(() ->
                        new ProfileNotFoundException("Profile not found."));

        return mapToResponse(profile);

    }

    @Override
    public ProfileResponse updateProfile(Long userId,
                                         ProfileRequest request) {

        UserProfile profile = repository.findByUserId(userId)
                .orElseThrow(() ->
                        new ProfileNotFoundException("Profile not found."));

        profile.setFullName(request.getFullName());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setAddress(request.getAddress());

        UserProfile updatedProfile = repository.save(profile);

        return mapToResponse(updatedProfile);

    }

    @Override
    public void deleteProfile(Long userId) {

        if (!repository.existsByUserId(userId)) {
            throw new ProfileNotFoundException("Profile not found.");
        }

        repository.deleteByUserId(userId);

    }

    private ProfileResponse mapToResponse(UserProfile profile) {

        return ProfileResponse.builder()
                .profileId(profile.getProfileId())
                .userId(profile.getUserId())
                .fullName(profile.getFullName())
                .phoneNumber(profile.getPhoneNumber())
                .address(profile.getAddress())
                .role(profile.getRole())
                .build();

    }

}