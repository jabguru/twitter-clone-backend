package com.jabguru.TwitterClone.user;

import com.jabguru.TwitterClone.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ImageService imageService;

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@ModelAttribute User user, @RequestParam("profilePhoto") Optional<MultipartFile> profilePhoto, @RequestParam("bannerPhoto") Optional<MultipartFile> bannerPhoto){
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

        userService.saveUser(user);
        return new ResponseEntity<>("User saved successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        User user = userService.getUser(id);
        if (user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUserByName(String name){
        return new ResponseEntity<>(userService.searchUserByName(name), HttpStatus.OK);
    }

}
