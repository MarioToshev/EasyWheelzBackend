package com.example.easywheelz.buisness.impl;

import com.example.easywheelz.buisness.interfaces.AccessToken.AccessTokenDecoder;
import com.example.easywheelz.buisness.interfaces.AccessToken.AccessTokenEncoder;
import com.example.easywheelz.customExeptions.InvalidAccessTokenException;
import com.example.easywheelz.domain.AccessToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class AccessTokenEncoderDecoderImplTest {
    private static final String SECRET_KEY = "E91E158E4C6656F68B1B5D1C316766DE98D2AD6EF3BFB44F78E9CFCDF5";


    @Mock
    private AccessToken accessToken;
    @Mock
    private AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder;



    @Test
     void testEncode() {
        Instant now = Instant.now();


        String expectedToken = Jwts.builder()
                .setSubject("testSubject")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .claim("roles", Collections.singletonList("testRole"))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();


        when(accessTokenEncoderDecoder.encode(accessToken)).thenReturn(expectedToken);

        String actualToken = accessTokenEncoderDecoder.encode(accessToken).toString();


        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testDecode() {
        String token = Jwts.builder()
                .setSubject("testSubject")
                .claim("roles", Collections.singletonList("testRole"))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();

        when(accessTokenEncoderDecoder.decode(token)).thenReturn(accessToken);

        AccessToken decodedAccessToken = accessTokenEncoderDecoder.decode(token);
        assertEquals(accessToken, decodedAccessToken);
    }
}







