package com.openclassrooms.mddapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.models.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<CommentDto, Comment> {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "user", ignore = true),
        @Mapping(target = "post", ignore = true),
    })
    public abstract Comment toEntity(CommentDto commentDto);

    @Mappings({
        @Mapping(source = "user.name", target = "userName"),
    })
    public abstract CommentDto toDto(Comment comment);

}
