package com.openclassrooms.mddapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicDto {
    private Integer id;

    private String title;

    private String description;
}
