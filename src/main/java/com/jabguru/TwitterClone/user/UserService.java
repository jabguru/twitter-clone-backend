package com.jabguru.TwitterClone.user;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean saveUser(Integer id, User updatedUser, Optional<MultipartFile> profilePhoto, Optional<MultipartFile> bannerPhoto);
    User getUser(Integer id);
    List<User> searchUserByName(String name);
    // Stream<RealtimeMessage> getLatestUserProfileData();
}
