package com.logistics.userservice.service;

import java.util.UUID;
import org.springframework.stereotype.Service;

import com.logistics.userservice.dto.ProfileRequest;
import com.logistics.userservice.dto.ProfileResponse;
import com.logistics.userservice.entity.UserProfile;
import com.logistics.userservice.exception.ProfileAlreadyExistsException;
import com.logistics.userservice.exception.ProfileNotFoundException;
import com.logistics.userservice.repository.UserProfileRepository;
import com.logistics.userservice.service.UserProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;

@Override
public ProfileResponse createProfile(UUID userId,String role ,ProfileRequest request) {

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
    public ProfileResponse getProfile(UUID userId) {

        UserProfile profile = repository.findByUserId(userId)
                .orElseThrow(() ->
                        new ProfileNotFoundException("Profile not found."));

        return mapToResponse(profile);

    }

    @Override
    public ProfileResponse updateProfile(UUID userId,ProfileRequest request) {

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
    public void deleteProfile(UUID userId) {

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