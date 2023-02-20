package com.lunatic.examportalbackend.services;

import com.lunatic.examportalbackend.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    User getUser(Long userId);

    List<User> getUsers();

    List<User> getUserByKeyword(String keyword);

    User updateUser(User newUser);

    User updatePassword(Long user, String oldPassword, String newPassword);

    void deleteUser(Long userId);
}
