package com.jabguru.TwitterClone.auth.refreshtoken;

import com.jabguru.TwitterClone.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createRefreshToken(User user){
        String token = UUID.randomUUID().toString();
        Instant expiryDate = Instant.now().plusSeconds(60 * 60 * 24);
        Optional<RefreshToken> existingToken = refreshTokenRepository.findByUserId(user.getId());

        RefreshToken refreshToken = existingToken.orElseGet(() -> RefreshToken.builder()
                .user(user)
                .token(token)
                .expiryDate(expiryDate) // set expiry of refresh token to 24hrs - you can configure it application.properties file
                .build());

        if (existingToken.isPresent()) {
            refreshToken.setToken(token);
            refreshToken.setExpiryDate(expiryDate);
        }

        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

}