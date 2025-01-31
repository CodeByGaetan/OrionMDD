package com.openclassrooms.mddapi.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Users", description = "API for user information")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * Get the current user info
     * 
     * @return the email and name of the current user
     */
    @Operation(summary = "Get the current user info")
    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getCurrentUser();

        return ResponseEntity.ok().body(userMapper.toDto(user));
    }
    
    /**
     * Subscribe the user to a topic
     * 
     * @param id The number identifying the topic we want to subscribe on
     * @return The updated subscribed topics Ids list
     */
    @Operation(summary = "Subscribe the user to a topic")
    @Transactional
    @PostMapping("/user/topics/{id}")
    public ResponseEntity<?> subscribeTopic(@PathVariable Integer id) {
        try {
            userService.subscribeTopic(id);
            return ResponseEntity.ok().body(userService.getSubscribedTopicIds());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Unsubscribe the user to a topic
     * 
     * @param id The number identifying the topic we want to unsubscribe from
     * @return The updated subscribed topics Ids list
     */
    @Operation(summary = "Unsubscribe the user to a topic")
    @Transactional
    @DeleteMapping("/user/topics/{id}")
    public ResponseEntity<?> unSubscribeTopic(@PathVariable Integer id) {
        try {
            userService.unSubscribeTopic(id);
            // Return updated subscribed topic ids list
            return ResponseEntity.ok().body(userService.getSubscribedTopicIds());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
