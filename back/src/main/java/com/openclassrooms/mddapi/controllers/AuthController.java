package com.openclassrooms.mddapi.controllers;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.services.AuthService;
import com.openclassrooms.mddapi.validations.groups.SignInEmailValidation;
import com.openclassrooms.mddapi.validations.groups.SignInNameValidation;
import com.openclassrooms.mddapi.validations.groups.SignUpValidation;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private Validator validator;

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signUp(@Validated(SignUpValidation.class) @RequestBody UserDto userDto) {

        try {
            String jwtResponse = authService.signUp(userDto);
            return ResponseEntity.ok().body(jwtResponse);
        } catch (Exception e) {
            String message = e.getMessage();
            return ResponseEntity.badRequest().body(message);
        }

    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signIn(@RequestBody UserDto userDto) {

        Boolean signInEmailNotValid = !validator.validate(userDto, SignInEmailValidation.class).isEmpty();
        Boolean signInNameNotValid = !validator.validate(userDto, SignInNameValidation.class).isEmpty();

        if (signInEmailNotValid && signInNameNotValid) {
            return ResponseEntity.badRequest().build();
        }

        String jwtResponse = authService.signIn(userDto);

        return ResponseEntity.ok().body(jwtResponse);
    }

}
