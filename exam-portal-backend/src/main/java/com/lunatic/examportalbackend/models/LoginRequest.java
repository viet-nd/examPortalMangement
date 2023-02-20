package com.lunatic.examportalbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequest implements Serializable {
    private String username;
    private String password;
}
