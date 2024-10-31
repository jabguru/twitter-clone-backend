package com.jabguru.TwitterClone.user.impl;

import com.jabguru.TwitterClone.image.ImageService;
import com.jabguru.TwitterClone.user.User;
import com.jabguru.TwitterClone.user.UserRepository;
import com.jabguru.TwitterClone.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ImageService imageService;

    @Override
    public boolean saveUser(Integer id, User updatedUser, Optional<MultipartFile> profilePhoto, Optional<MultipartFile> bannerPhoto) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (updatedUser.getName() != null) {
                user.setName(updatedUser.getName());
            }
            if (updatedUser.getFollowers() != null) {
                user.setFollowers(updatedUser.getFollowers());
            }
            if (updatedUser.getFollowing() != null) {
                user.setFollowing(updatedUser.getFollowing());
            }

            if (updatedUser.getBio() != null) {
                user.setBio(updatedUser.getBio());
            }

            if (updatedUser.getIsTwitterBlue() != null) {
                user.setIsTwitterBlue(updatedUser.getIsTwitterBlue());
            }

            if(profilePhoto.isPresent()){
                String url;
                try {
                    url = imageService.saveImage(profilePhoto.get());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                user.setProfilePic(url);
            }

            if(bannerPhoto.isPresent()){
                String url;
                try {
                    url = imageService.saveImage(bannerPhoto.get());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                user.setBannerPic(url);
            }

            userRepository.save(user);
            return true;
        }
        return false;

    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> searchUserByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
}
