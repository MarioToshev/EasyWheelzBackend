package com.example.easywheelz.buisness.interfaces.accesstoken;

import com.example.easywheelz.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
