package com.lunatic.examportalbackend.services;

import com.lunatic.examportalbackend.models.LoginRequest;
import com.lunatic.examportalbackend.models.LoginResponse;
import com.lunatic.examportalbackend.models.User;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {
    User registerUserService(User user) throws Exception;

    User registerOfAdminService(String role, User user) throws Exception;

//    User registerUserService(User user, MultipartFile avatar) throws Exception;

    LoginResponse loginUserService(LoginRequest loginRequest) throws Exception;
}
