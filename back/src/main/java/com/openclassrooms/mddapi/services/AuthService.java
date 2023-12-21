package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.User;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public String signUp(UserDto userDto) {

        // Vérifier que l'email ou le name n'existe pas

        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setName(userDto.getName());
        // Password à encoder
        newUser.setPassword(userDto.getPassword());
        
        userService.saveUser(newUser);

        String jwt = "JeSuisUnTokenJwt";

        return jwt;
    }

    public String signIn(UserDto userDto) {

        String jwt = "JeSuisUnTokenJwt";

        return jwt;
    }
}
