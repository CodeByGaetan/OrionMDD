package com.openclassrooms.mddapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.repositories.TopicRepository;

@Service
public class TopicService {
    
    @Autowired
    private TopicRepository topicRepository;

    /**
     * Get all topics
     * 
     * @return All topics
     */
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    /**
     * Get a topic by its Id
     * 
     * @param id The number identifying the topic
     * @return The identified topic
     */
    public Topic getById(Integer id) {
        return topicRepository.findById(id).orElse(null);
    }

}
