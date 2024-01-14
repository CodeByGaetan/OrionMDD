package com.openclassrooms.mddapi.controllers;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.others.exceptions.AuthException;
import com.openclassrooms.mddapi.others.requests.LoginRequest;
import com.openclassrooms.mddapi.others.responses.AuthErrorResponse;
import com.openclassrooms.mddapi.others.responses.AuthJwtResponse;
import com.openclassrooms.mddapi.others.validations.groups.SignInEmailValidation;
import com.openclassrooms.mddapi.others.validations.groups.SignInNotBlanckValidation;
import com.openclassrooms.mddapi.others.validations.groups.SignUpPasswordValidation;
import com.openclassrooms.mddapi.others.validations.groups.SignUpValidation;
import com.openclassrooms.mddapi.others.validations.groups.UpdateUserValidation;
import com.openclassrooms.mddapi.services.AuthService;

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
     * Register and authenticate a new user
     *  
     * @param userDto containing a valid email, user name and password
     * @return JWT token to authenticate future requests
     */
    @Operation(summary = "Sign up a user")
    @PostMapping("/auth/signup")
    public ResponseEntity<?> signUp(@Validated(SignUpValidation.class) @RequestBody UserDto userDto) {

        Boolean signUpPasswordValid = validator.validate(userDto,
                SignUpPasswordValidation.class).isEmpty();
        if (!signUpPasswordValid) {
            return ResponseEntity.badRequest().body(new AuthErrorResponse("Password not valid", 3));
        }

        try {
            String jwtResponse = authService.signUp(userDto);
            return ResponseEntity.ok().body(new AuthJwtResponse(jwtResponse));
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(new AuthErrorResponse(e.getMessage(), e.getCodeError()));
        }
    }

    /**
     * Authenticate an existing user from email or user name
     * 
     * @param loginRequest containing a valid email or user name and password
     * @return JWT token to authenticate future requests
     */
    @Operation(summary = "Sign in a user with email or user name")
    @PostMapping("/auth/signin")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) {
               
        Boolean signInNotBlanckValid = validator.validate(loginRequest,
                SignInNotBlanckValidation.class).isEmpty();

        Boolean signInEmailValid = validator.validate(loginRequest,
                SignInEmailValidation.class).isEmpty();

        if(!signInNotBlanckValid) {
            return ResponseEntity.badRequest().body(new AuthErrorResponse("One or more fields are empty", 0));
        } else if (signInEmailValid) {
            try {
                String jwtResponse = authService.signInWithEmail(loginRequest);
                return ResponseEntity.ok().body(new AuthJwtResponse(jwtResponse));
            } catch (AuthException e) {
                return ResponseEntity.badRequest().body(new AuthErrorResponse(e.getMessage(), e.getCodeError()));
            }
        } else {
            try {
                String jwtResponse = authService.signInWithName(loginRequest);
                return ResponseEntity.ok().body(new AuthJwtResponse(jwtResponse));
            } catch (AuthException e) {
                return ResponseEntity.badRequest().body(new AuthErrorResponse(e.getMessage(), e.getCodeError()));
            }
        }
    }

    /**
     * Update the current user info from a valid request
     * 
     * @param userDto The update request containing the new email and user name
     * @return Ok status response
     */
    @Operation(summary = "Update the current user info")
    @PutMapping("/auth/user")
    public ResponseEntity<?> updateCurrentUser(@Validated(UpdateUserValidation.class) @RequestBody UserDto userDto) {
        try {
            String jwtResponse = authService.updateCurrentUser(userDto);
            return ResponseEntity.ok().body(new AuthJwtResponse(jwtResponse));
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(new AuthErrorResponse(e.getMessage(), e.getCodeError()));
        }
    }

}
