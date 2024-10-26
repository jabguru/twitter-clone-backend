package com.jabguru.TwitterClone.auth;

import com.jabguru.TwitterClone.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
   private User user;
   private String accessToken;
   private String refreshToken;
}
