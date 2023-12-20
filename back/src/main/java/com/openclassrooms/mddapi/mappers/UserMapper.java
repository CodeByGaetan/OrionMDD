package com.openclassrooms.mddapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

    // @Mappings({
    //         @Mapping(target = "topic", expression = "java( topicService.getById(postDto.getTopic_id()) )"),
    //         @Mapping(target = "user", expression = "java( postDto.getUser_id() != null ? userService.getById(postDto.getUser_id()) : null )"),
    //         @Mapping(target = "comments", ignore = true),
    // })
    // public User toEntity(UserDto userDto);

    @Mappings({
            @Mapping(target = "topicIds", expression = "java( user.getTopics().stream().map(topic -> topic.getId()).toList() )"),
    })
    public UserDto toDto(User user);

    // public void test(User user) {
    //     user.getTopics().stream().map(topic -> topic.getId()).toList()
    // }
}
