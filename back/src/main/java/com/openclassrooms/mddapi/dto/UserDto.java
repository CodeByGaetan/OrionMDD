package com.openclassrooms.mddapi.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    private List<Integer> topicIds;
}
