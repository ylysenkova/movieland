package com.ylysenkova.movieland.service;


import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import com.ylysenkova.movieland.model.Token;
import com.ylysenkova.movieland.model.User;

import java.util.Optional;
import java.util.UUID;

public interface AuthenticationService {

    Pair<UUID, Token> authenticationUser(String email, String password);

    String getUserMailByUuid(UUID uuid);

    User getUserByUuid(UUID uuid);

    boolean isAlive (UUID uuid);

    void logout(UUID uuid);
}
