package com.ylysenkova.movieland.web.response;


import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import com.ylysenkova.movieland.model.Token;

import java.util.UUID;

public class AuthenticationResponse {
    private UUID uuid;
    private String nickname;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(Pair<UUID, Token> uuidTokenPair) {
        this.uuid = uuidTokenPair.getKey();
        this.nickname = uuidTokenPair.getValue().getUser().getName();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
