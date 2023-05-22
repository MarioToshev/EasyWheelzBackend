package com.example.easywheelz.buisness.impl;

import com.example.easywheelz.domain.AccessToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class AccessTokenEncoderDecoderImplTest {
    private static final String SECRET_KEY = "E91E158E4C6656F68B1B5D1C316766DE98D2AD6EF3BFB44F78E9CFCDF5";
    private  Key key;
    @Mock
    private AccessToken accessToken;

    private AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder;


    @Test
     void testEncode() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        accessTokenEncoderDecoder = new AccessTokenEncoderDecoderImpl(SECRET_KEY);
        Instant now = Instant.now();
        when(accessToken.getSubject()).thenReturn("testSubject");
        when(accessToken.getRoles()).thenReturn(Collections.singletonList("testRole"));
        when(accessToken.getUserId()).thenReturn(123L);

        String expectedToken = Jwts.builder()
                .setSubject("testSubject")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .claim("roles", Collections.singletonList("testRole"))
                .claim("userId", 123L)
                .signWith(key)
                .compact();

        String actualToken = accessTokenEncoderDecoder.encode(accessToken);

        assertEquals(expectedToken, actualToken);
    }

    @Test
     void testDecode() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        accessTokenEncoderDecoder = new AccessTokenEncoderDecoderImpl(SECRET_KEY);


        String token = Jwts.builder()
                .setSubject("testSubject")
                .claim("roles", Collections.singletonList("testRole"))
                .claim("userId", 123L)
                .signWith(key)
                .compact();




        AccessToken decodedAccessToken = accessTokenEncoderDecoder.decode(token);

        assertEquals(decodedAccessToken.getUserId(), 123L);
        assertEquals(decodedAccessToken.getRoles(), Collections.singletonList("testRole"));
        assertEquals(decodedAccessToken.getSubject(), "testSubject");

    }
}







