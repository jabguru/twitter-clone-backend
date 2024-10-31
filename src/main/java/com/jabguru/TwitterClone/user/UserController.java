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

    @PostMapping("/update/{id}")
    public ResponseEntity<String> saveUser(@ModelAttribute User user, @RequestParam("profilePhoto") Optional<MultipartFile> profilePhoto, @RequestParam("bannerPhoto") Optional<MultipartFile> bannerPhoto, @PathVariable Integer id){
        boolean updated = userService.saveUser(id, user, profilePhoto, bannerPhoto);
        if(updated)
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        User user = userService.getUser(id);
        if (user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUserByName(@RequestParam String name){
        return new ResponseEntity<>(userService.searchUserByName(name), HttpStatus.OK);
    }

}
