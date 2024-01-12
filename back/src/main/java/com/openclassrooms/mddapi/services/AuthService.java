package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.others.exceptions.AuthException;
import com.openclassrooms.mddapi.others.requests.LoginRequest;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    /**
     * Register and authenticate a new user
     * 
     * @param userDto containing the user email, name and password
     * @return JWT token to authenticate the future requests
     * @throws AuthException if the email or user name already exists in the database
     */
    public String signUp(UserDto userDto) throws AuthException {

        if (userService.existsByEmail(userDto.getEmail())) {
            throw new AuthException("Email already used", 1);
        }

        if (userService.existsByName(userDto.getName())) {
            throw new AuthException("User name already used", 2);
        }

        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setName(userDto.getName());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        
        userService.saveUser(newUser);

        return jwtService.generateToken(newUser.getEmail());
    }

    // Sign In functions

    private String authenticateUser(String email, String password) throws AuthException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return jwtService.generateToken(authentication.getName());
        } catch (Exception e) {
            throw new AuthException("Wrong password", 3);
        }
    }

    /**
     * Authenticate an existing user from its email
     * 
     * @param loginRequest containing the user email and password
     * @return JWT token to authenticate the future requests
     * @throws AuthException if the email is unknown or the password is incorrect
     */
    public String signInWithEmail(LoginRequest loginRequest) throws AuthException {

        User user = userService.findByEmail(loginRequest.getUsername());

        if (user == null) {
            throw new AuthException("Unknown email", 1);
        }

        return authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

    /**
     * Authenticate an existing user from its name
     * 
     * @param loginRequest containing the user name and password
     * @return JWT token to authenticate the future requests
     * @throws AuthException if the name is unknown or the password is incorrect
     */
    public String signInWithName(LoginRequest loginRequest) throws AuthException {

        User user = userService.findByName(loginRequest.getUsername());

        if (user == null) {
            throw new AuthException("Unknown user name", 2);
        }

        return authenticateUser(user.getEmail(), loginRequest.getPassword());
    }
    
}