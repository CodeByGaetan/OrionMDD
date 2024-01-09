package com.openclassrooms.mddapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

    // Modifier cette méthode pour récuperer l'utilisateur dans le Security Contexte
    public User getCurrentUser() {
        User user = getById(1);
        return user;
    }

    public List<Integer> getSubscribedTopicIds() {
        List<Topic> subscribedTopics = getCurrentUser().getTopics();

        return subscribedTopics.stream().map(topic -> topic.getId()).collect(Collectors.toList());
    }

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

    public User updateCurrentUser(UserDto userDto) {
        User user = getCurrentUser();

        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());

        return userRepository.save(user);
    }

    // Utiliser directement le User Repository dans AuthService ??

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User findByName(String name) {
        return userRepository.findByName(name).orElse(null);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

}
