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
import com.openclassrooms.mddapi.exceptions.AuthException;
import com.openclassrooms.mddapi.requests.LoginRequest;
import com.openclassrooms.mddapi.responses.AuthErrorResponse;
import com.openclassrooms.mddapi.responses.AuthJwtResponse;
import com.openclassrooms.mddapi.services.AuthService;
import com.openclassrooms.mddapi.validations.groups.SignInEmailValidation;
import com.openclassrooms.mddapi.validations.groups.SignInNotBlanckValidation;
import com.openclassrooms.mddapi.validations.groups.SignUpValidation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication", description = "API for authentication operations")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private Validator validator;

    /**
     * 
     * @param userDto
     * @return
     */
    @Operation(summary = "Sign up a user")
    @PostMapping("/auth/signup")
    public ResponseEntity<?> signUp(@Validated(SignUpValidation.class) @RequestBody UserDto userDto) {
        try {
            String jwtResponse = authService.signUp(userDto);
            return ResponseEntity.ok().body(new AuthJwtResponse(jwtResponse));
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(new AuthErrorResponse(e.getMessage(), e.getCodeError()));
        }
    }

    /**
     * 
     * @param loginRequest
     * @return
     */
    @Operation(summary = "Sign in a user")
    @PostMapping("/auth/signin")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) {
               
        Boolean signInNotBlanckValid = validator.validate(loginRequest,
                SignInNotBlanckValidation.class).isEmpty();

        Boolean signInEmailValid = validator.validate(loginRequest,
                SignInEmailValidation.class).isEmpty();

        if(!signInNotBlanckValid) {
            return ResponseEntity.badRequest().body(new AuthErrorResponse("One or more fields are empty", 0));

        // Essayer de se connecter avec un email
        } else if (signInEmailValid) {
            try {
                String jwtResponse = authService.signInWithEmail(loginRequest);
                return ResponseEntity.ok().body(new AuthJwtResponse(jwtResponse));
            } catch (AuthException e) {
                return ResponseEntity.badRequest().body(new AuthErrorResponse(e.getMessage(), e.getCodeError()));
            }

        // Essayer de se connecter avec un name
        } else {
            try {
                String jwtResponse = authService.signInWithName(loginRequest);
                return ResponseEntity.ok().body(new AuthJwtResponse(jwtResponse));
            } catch (AuthException e) {
                return ResponseEntity.badRequest().body(new AuthErrorResponse(e.getMessage(), e.getCodeError()));
            }

        }
    }

}
