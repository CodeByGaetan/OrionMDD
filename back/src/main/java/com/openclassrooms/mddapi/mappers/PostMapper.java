package com.openclassrooms.mddapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;

@Mapper(componentModel = "spring")
public abstract class PostMapper implements EntityMapper<PostDto, Post> {

        @Autowired
        TopicService topicService;

        @Autowired
        UserService userService;

        @Mappings({
                @Mapping(target = "topic", expression = "java( topicService.getById(postDto.getTopicId()) )"),
                @Mapping(target = "user", ignore = true),
                @Mapping(target = "comments", ignore = true)
        })
        public abstract Post toEntity(PostDto postDto);

        @Mappings({
                @Mapping(source = "topic.id", target = "topicId"),
                @Mapping(source = "user.name", target = "userName"),
        })
        public abstract PostDto toDto(Post post);

}
