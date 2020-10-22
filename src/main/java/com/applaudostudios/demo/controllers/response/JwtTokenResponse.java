package com.applaudostudios.demo.controllers.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtTokenResponse {

    @NonNull
    private String accessToken;

    private String tokenType = "Bearer";
}
