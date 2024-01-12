package com.openclassrooms.mddapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicService topicService;

    public User getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Get the current user, identified by the JWT token of the request
     * 
     * @return The current user
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = findByEmail(userEmail);

        return user;
    }

    /**
     * Get the user subscribed topic Ids
     * 
     * @return List of subscribed topic Ids
     */
    public List<Integer> getSubscribedTopicIds() {
        List<Topic> subscribedTopics = getCurrentUser().getTopics();

        return subscribedTopics.stream().map(topic -> topic.getId()).collect(Collectors.toList());
    }

    /**
     * Subscribe the user to a topic
     * 
     * @param id The number identifying the topic we want to subscribe on
     * @throws Exception If the topics is not found or if the topic is already subscribed
     */
    public void subscribeTopic(Integer id) throws Exception {
        User user = getCurrentUser();
        Topic topic = topicService.getById(id);

        if (topic == null) {
            throw new Exception("Topic not found");
        }

        if (user.getTopics().contains(topic)) {
            throw new Exception("Topic already susbscribed");
        }

        user.getTopics().add(topic);
        userRepository.save(user);
    }

    /**
     * Unsubscribe the user to a topic
     * 
     * @param id The number identifying the topic we want to unsubscribe from
     * @throws Exception If the topics is not found or if the topic is already not subscribed
     */
    public void unSubscribeTopic(Integer id) throws Exception {
        User user = getCurrentUser();
        Topic topic = topicService.getById(id);

        if (topic == null) {
            throw new Exception("Topic not found");
        }

        if (!user.getTopics().contains(topic)) {
            throw new Exception("Topic has never been susbscribed");
        }

        user.getTopics().remove(topic);
        userRepository.save(user);
    }

    /**
     * Update the current user information
     * 
     * @param userDto The user info containing the new email and user name
     * @return The updated user from the database
     */
    public User updateCurrentUser(UserDto userDto) {
        User user = getCurrentUser();

        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());

        return userRepository.save(user);
    }

    /**
     * Save the user in the database
     * 
     * @param user The user to save
     * @return Saved user from the database
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Find a user in the database from its email
     * 
     * @param email The user email
     * @return The user founded or a null value
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    /**
     * Find a user in the database from its name
     * 
     * @param email The user name
     * @return The user founded or a null value
     */
    public User findByName(String name) {
        return userRepository.findByName(name).orElse(null);
    }

    /**
     * Check if a user exists with an email
     * 
     * @param email The email to check
     * @return True if the user exists
     */
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Check if a user exists with an name
     * 
     * @param name The name to check
     * @return True if the user exists
     */
    public Boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

}
