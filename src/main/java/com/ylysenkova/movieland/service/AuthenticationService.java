package com.ylysenkova.movieland.service;


import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import com.ylysenkova.movieland.model.Token;

import java.util.UUID;

public interface AuthenticationService {

    Pair<UUID, Token> authenticationUser(String email, String password);

    String getUserMailByUuid(UUID uuid);

    boolean isAlive (UUID uuid);

    void logout(UUID uuid);
}
