package com.logistics.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {

    private Long profileId;

    private Long userId;

    private String fullName;

    private String phoneNumber;

    private String address;

    private String role;

}