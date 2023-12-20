package com.openclassrooms.mddapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TopicDto {
    private Integer id;

    private String title;

    private String description;
}
