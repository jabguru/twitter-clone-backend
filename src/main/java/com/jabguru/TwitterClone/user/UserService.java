package com.jabguru.TwitterClone.user;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    User getUser(Integer id);
    List<User> searchUserByName(String name);
    // Stream<RealtimeMessage> getLatestUserProfileData();
}
