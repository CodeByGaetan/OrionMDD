package com.openclassrooms.mddapi.controllers;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.responses.MessageResponse;
import com.openclassrooms.mddapi.services.AuthService;
import com.openclassrooms.mddapi.validations.groups.SignInEmailValidation;
import com.openclassrooms.mddapi.validations.groups.SignInNameValidation;
import com.openclassrooms.mddapi.validations.groups.SignUpValidation;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private Validator validator;

    /**
     * 
     * @param userDto coucou
     * @return
     */
    @PostMapping("/auth/signup")
    public ResponseEntity<?> signUp(@Validated(SignUpValidation.class) @RequestBody UserDto userDto) {

        try {
            String jwtResponse = authService.signUp(userDto);
            return ResponseEntity.ok().body(new MessageResponse(jwtResponse));

        } catch (Exception e) {
            String message = e.getMessage();
            return ResponseEntity.badRequest().body(new MessageResponse(message, 1));
        }

    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signIn(@RequestBody UserDto userDto) {

        Boolean signInEmailNotValid = !validator.validate(userDto, SignInEmailValidation.class).isEmpty();
        Boolean signInNameNotValid = !validator.validate(userDto, SignInNameValidation.class).isEmpty();

        if (signInEmailNotValid && signInNameNotValid) {
            // Ajouter les cas d'erreur possible
            return ResponseEntity.badRequest().body(new MessageResponse("Email, nom ou mot de passe non valide", 1));
        }

        String jwtResponse = authService.signIn(userDto);

        return ResponseEntity.ok().body(new MessageResponse(jwtResponse));
    }

}
