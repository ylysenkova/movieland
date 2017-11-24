package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.dao.UserDao;
import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import com.ylysenkova.movieland.model.Token;
import com.ylysenkova.movieland.model.User;
import com.ylysenkova.movieland.service.AuthenticationService;
import com.ylysenkova.movieland.web.exceptions.AuthentificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Map<UUID, Token> tokenCache= new ConcurrentHashMap<>();

    @Autowired
    private UserDao userDao;


    @Override
    public Pair<UUID, Token> authenticationUser(String email, String password) {
        logger.debug("Token is creating...");

        LocalDateTime expiredTime = LocalDateTime.now().plusHours(2);
        User user = userDao.getUser(email, password);

        UUID uuid = UUID.randomUUID();
        Token token = new Token(user,  expiredTime);
        tokenCache.put(uuid, token);
        Pair<UUID, Token> uuidTokenPair = new Pair<>();
        uuidTokenPair.setKey(uuid);
        uuidTokenPair.setValue(token);
        return uuidTokenPair;
    }

    @Override
    public void logout(UUID uuid) {
        tokenCache.remove(uuid);

    }

    public boolean isAlive (UUID uuid) {
        if(tokenCache.containsKey(uuid)) {
            Token token = tokenCache.get(uuid);
            if (token.getExpiredTime().isBefore(LocalDateTime.now())) {
            tokenCache.remove(uuid);
            return false;
            }
            return true;
        }
        return false;
    }

    @Scheduled(fixedRateString = "${cache.user.remove}")
    public void removeExpiredToken () {
        for(Map.Entry<UUID, Token> serchExpired : tokenCache.entrySet()) {
            isAlive(serchExpired.getKey());
        }
    }
}
