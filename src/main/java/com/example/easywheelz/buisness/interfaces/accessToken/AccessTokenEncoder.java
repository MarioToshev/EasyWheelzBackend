package com.example.easywheelz.buisness.interfaces.accessToken;

import com.example.easywheelz.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);

}
