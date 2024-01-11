package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.exceptions.AuthException;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.requests.LoginRequest;

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

    public String signInWithEmail(LoginRequest loginRequest) throws AuthException {

        User user = userService.findByEmail(loginRequest.getUsername());

        if (user == null) {
            throw new AuthException("Unknown email", 1);
        }

        return authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

    public String signInWithName(LoginRequest loginRequest) throws AuthException {

        User user = userService.findByName(loginRequest.getUsername());

        if (user == null) {
            throw new AuthException("Unknown user name", 2);
        }

        return authenticateUser(user.getEmail(), loginRequest.getPassword());
    }
    
}