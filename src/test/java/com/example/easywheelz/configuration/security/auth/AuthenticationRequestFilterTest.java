package com.example.easywheelz.configuration.security.auth;

import com.example.easywheelz.buisness.interfaces.AccessToken.AccessTokenDecoder;
import com.example.easywheelz.customExeptions.IncorrectUserCredentialsError;
import com.example.easywheelz.customExeptions.InvalidAccessTokenException;
import com.example.easywheelz.domain.AccessToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationRequestFilterTest {
    @Mock
   private AccessTokenDecoder decoder;
    @Mock
    private HttpServletRequest request ;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain chain;
    @InjectMocks
    private AuthenticationRequestFilter filter ;


    @Test
     void testDoFilterInternal_NoAuthorizationHeader() throws ServletException, IOException {
        filter.doFilterInternal(request, response, chain);
        verify(chain).doFilter(request, response);
    }
    @Test
     void testDoFilterInternal_AuthorizationHeaderNotBearer() throws ServletException, IOException {

        when(request.getHeader("Authorization")).thenReturn("Basic username:password");

        filter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
    }

    @Test
     void testDoFilterInternal_InvalidAccessToken() throws ServletException, IOException, InvalidAccessTokenException {

        when(request.getHeader("Authorization")).thenReturn("Bearer invalidtoken");
        when(decoder.decode("invalidtoken")).thenThrow(new InvalidAccessTokenException(""));

        filter.doFilterInternal(request, response, chain);

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}