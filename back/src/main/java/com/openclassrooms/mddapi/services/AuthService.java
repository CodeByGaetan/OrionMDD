package com.openclassrooms.mddapi.services;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.UserDto;

@Service
public class AuthService {

    public String signUp(UserDto userDto) {
        return "JeSuisUnTokenJwt";
    }
}
