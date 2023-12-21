package com.openclassrooms.mddapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.services.AuthService;
import com.openclassrooms.mddapi.validations.SignUpValidation;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService; 

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signUp(@Validated(SignUpValidation.class) @RequestBody UserDto userDto) {

        // Vérifier que la requete est complete
        
        // Vérifier que l'email ou le name n'existe pas

        // Vérifier le mot de passe

        String jwtResponse = authService.signUp(userDto);
        
        return ResponseEntity.ok().body(jwtResponse);
    }

}
