package com.openclassrooms.mddapi.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.UserService;
import com.openclassrooms.mddapi.validations.groups.UpdateUserValidation;

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
     * 
     * @return
     */
    @Operation(summary = "Get the current user info")
    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getCurrentUser();

        return ResponseEntity.ok().body(userMapper.toDto(user));
    }

    /**
     * 
     * @param userDto
     * @return
     */
    @Operation(summary = "Update the current user info")
    @PutMapping("/user")
    public ResponseEntity<?> updateCurrentUser(@Validated(UpdateUserValidation.class) @RequestBody UserDto userDto) {
        try {
            userService.updateCurrentUser(userDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 
     * @param id
     * @return
     */
    @Operation(summary = "Subscribe the user to a topic")
    @Transactional
    @PostMapping("/user/topics/{id}")
    public ResponseEntity<?> subscribeTopic(@PathVariable Integer id) {
        try {
            userService.subscribeTopic(id);
            // Return updated subscribed topic ids list
            return ResponseEntity.ok().body(userService.getSubscribedTopicIds());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 
     * @param id
     * @return
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
