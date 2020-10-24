package com.applaudostudios.demo.controllers;


import com.applaudostudios.demo.config.security.OauthJwtTokenProvider;
import com.applaudostudios.demo.controllers.request.LoginRequest;
import com.applaudostudios.demo.controllers.response.JwtTokenResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @NonNull
    private final AuthenticationManager authenticationManager;

    @NonNull
    private final OauthJwtTokenProvider tokenProvider;

    /**
     * Method to obtain jwt token
     *
     * @param loginRequest
     * @return ResponseEntity<?>
     */
    @PostMapping
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtTokenResponse(jwt));
    }
}
