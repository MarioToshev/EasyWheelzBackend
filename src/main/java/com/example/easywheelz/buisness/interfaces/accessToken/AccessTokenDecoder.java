package com.example.easywheelz.buisness.interfaces.AccessToken;

import com.example.easywheelz.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
