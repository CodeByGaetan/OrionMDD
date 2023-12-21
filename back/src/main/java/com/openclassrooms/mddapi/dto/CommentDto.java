package com.openclassrooms.mddapi.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {

    @NotBlank
    private String content;

    private String user_name;
    
}
