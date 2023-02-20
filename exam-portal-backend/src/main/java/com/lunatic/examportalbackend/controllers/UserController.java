package com.lunatic.examportalbackend.controllers;

import com.lunatic.examportalbackend.models.User;
import com.lunatic.examportalbackend.services.AuthService;
import com.lunatic.examportalbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User registerManager(@RequestParam("role") String role, @RequestBody User user) throws Exception {
        return authService.registerOfAdminService(role, user);
    }

    @GetMapping("/")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping(params = "keyword")
    public ResponseEntity<?> getUserBySearchKey(@RequestParam String keyword) {
        List<User> userList = userService.getUserByKeyword(keyword);
        if (userList != null) {
            return ResponseEntity.ok(userList);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with keyword : " + String.valueOf(keyword) + ", doesn't exists");
    }

    //not change password of user
    @PutMapping(value = "/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User newUser) {
        if (userService.getUser(userId) != null) {
            return ResponseEntity.ok(userService.updateUser(newUser));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with id : " + String.valueOf(userId) + ", doesn't exists");
    }

    //change password of user
    @PutMapping(value = "/{userId}", params = {"oldPassword", "newPassword"})
    public ResponseEntity<?> updatePassword(@PathVariable Long userId, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        if (userService.getUser(userId) != null) {
            return ResponseEntity.ok(userService.updatePassword(userId, oldPassword, newPassword));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with id : " + String.valueOf(userId) + ", doesn't exists");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(true);
    }
}
