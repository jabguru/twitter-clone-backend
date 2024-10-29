package com.jabguru.TwitterClone.auth.refreshtoken;

import com.jabguru.TwitterClone.auth.AuthenticationResponse;
import com.jabguru.TwitterClone.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @PostMapping("/refreshToken")
    public AuthenticationResponse refreshToken(@RequestBody String refreshToken){
        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo);
                    return AuthenticationResponse.builder()
                            .accessToken(accessToken)
                            .user(userInfo)
                            .refreshToken(refreshToken).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }
}
