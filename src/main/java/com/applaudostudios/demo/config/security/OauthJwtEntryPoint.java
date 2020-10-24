package com.applaudostudios.demo.config.security;

import java.io.IOException;
import java.io.Serializable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Component
public class OauthJwtEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 8050563575927933690L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,

                         AuthenticationException authException) throws IOException {

        log.error("Error - Message - {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

    }
}
