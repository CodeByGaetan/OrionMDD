package com.openclassrooms.mddapi.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.openclassrooms.mddapi.dto.TopicDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class TopicsResponse {
    List<Integer> subTopicIds;
    List<TopicDto> topics;
}
