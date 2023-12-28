package com.openclassrooms.mddapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.responses.MessageResponse;
import com.openclassrooms.mddapi.services.UserService;
import com.openclassrooms.mddapi.validations.groups.UpdateUserValidation;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getCurrentUser();

        return ResponseEntity.ok().body(userMapper.toDto(user));
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateCurrentUser(@Validated(UpdateUserValidation.class) @RequestBody UserDto userDto) {
        try {
            userService.updateCurrentUser(userDto);
            return ResponseEntity.ok().body("User info updated !");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // A voir si ne pas plutot utiliser la fonction ci-avant plutot
    @GetMapping("/user/topics")
    public ResponseEntity<?> getUserSuscribedTopics() {
        User user = userService.getCurrentUser();

        UserDto userDto = userMapper.toDto(user);

        return ResponseEntity.ok().body(userDto.getTopicIds());
    }

    @PostMapping("/user/topics/{id}")
    public ResponseEntity<?> subscribeTopic(@PathVariable Integer id) {
        try {
            userService.subscribeTopic(id);
            return ResponseEntity.ok().body(new MessageResponse("Topic subscribed !"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/user/topics/{id}")
    public ResponseEntity<?> unSubscribeTopic(@PathVariable Integer id) {
        try {
            userService.unSubscribeTopic(id);
            return ResponseEntity.ok().body(new MessageResponse("Topic unsubscribed !"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
