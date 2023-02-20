package com.lunatic.examportalbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse implements Serializable {
    private User user;
    private String jwtToken;
//    private MultipartFile avatar;
}
