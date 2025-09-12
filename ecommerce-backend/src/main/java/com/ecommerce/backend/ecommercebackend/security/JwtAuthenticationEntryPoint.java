package com.ecommerce.backend.ecommercebackend.security;

import com.ecommerce.backend.ecommercebackend.util.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.error("Unauthorized error: {}", authException.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(false);
        apiResponse.setMessage("Unauthorized");
        apiResponse.setData("You are not authorized to access this resource.");

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(apiResponse);
        response.getWriter().write(jsonResponse);
    }
}
