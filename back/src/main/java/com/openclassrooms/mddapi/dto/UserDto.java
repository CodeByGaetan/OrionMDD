package com.openclassrooms.mddapi.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.openclassrooms.mddapi.validations.SignUpValidation;
import com.openclassrooms.mddapi.validations.ValidPassword;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    @NotBlank(groups = {SignUpValidation.class, })
    @Email(groups = {SignUpValidation.class})
    private String email;

    @NotBlank(groups = {SignUpValidation.class})
    private String name;

    @ValidPassword(groups = {SignUpValidation.class})
    private String password;

    private List<Integer> topicIds;
}
