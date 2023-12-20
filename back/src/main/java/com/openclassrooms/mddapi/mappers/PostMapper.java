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
            @Mapping(target = "topic", expression = "java( topicService.getById(postDto.getTopic_id()) )"),
            @Mapping(target = "user", expression = "java( postDto.getUser_id() != null ? userService.getById(postDto.getUser_id()) : null )"),
            // @Mapping(target = "createdAt", ignore = true),
    })
    public abstract Post toEntity(PostDto postDto);

    @Mappings({
            @Mapping(source = "topic.id", target = "topic_id"),
            @Mapping(source = "user.id", target = "user_id"),
    })
    public abstract PostDto toDto(Post post);

}
