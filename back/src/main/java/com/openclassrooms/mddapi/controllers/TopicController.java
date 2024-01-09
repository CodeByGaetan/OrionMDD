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
import com.openclassrooms.mddapi.responses.TopicsResponse;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;

@RestController
@RequestMapping("/api")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicMapper topicMapper;

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
