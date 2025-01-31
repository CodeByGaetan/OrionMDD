package com.openclassrooms.mddapi.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.mappers.TopicMapper;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.others.responses.TopicsResponse;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Topics", description = "API for CRUD operations on Topics")
@RestController
@RequestMapping("/api")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicMapper topicMapper;

    /**
     * Get all topics and user subscibed topics
     * 
     * @param userOnly Whether only the user subscribed topics or all the topics
     * @return All topics and subscribed topic Ids Or only subscribed topics
     */
    @Operation(summary = "Get all topics filtered")
    @Transactional
    @GetMapping("/topics")
    public ResponseEntity<?> getAll(@RequestParam boolean userOnly) {

        TopicsResponse topicsResponse = new TopicsResponse();
        if (userOnly) {
            List<Topic> subscribedTopics = userService.getCurrentUser().getTopics();
            List<TopicDto> topicDtos = topicMapper.toDto(subscribedTopics);
            topicsResponse.setTopics(topicDtos);
        } else {
            List<TopicDto> topicDtos = topicMapper.toDto(topicService.getAll());
            topicsResponse.setTopics(topicDtos);
            List<Integer> subscribedTopicIds = userService.getSubscribedTopicIds();
            topicsResponse.setSubTopicIds(subscribedTopicIds);
        }

        return ResponseEntity.ok().body(topicsResponse);
    }
}
