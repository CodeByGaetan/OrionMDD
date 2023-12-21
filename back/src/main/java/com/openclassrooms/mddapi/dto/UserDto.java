package com.openclassrooms.mddapi.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.openclassrooms.mddapi.validations.groups.SignInEmailValidation;
import com.openclassrooms.mddapi.validations.groups.SignInNameValidation;
import com.openclassrooms.mddapi.validations.groups.SignUpValidation;
import com.openclassrooms.mddapi.validations.groups.UpdateUserValidation;
import com.openclassrooms.mddapi.validations.password.ValidPassword;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    @NotBlank(groups = {SignUpValidation.class, SignInEmailValidation.class, UpdateUserValidation.class})
    @Email(groups = {SignUpValidation.class, SignInEmailValidation.class, UpdateUserValidation.class})
    private String email;

    @NotBlank(groups = {SignUpValidation.class, SignInNameValidation.class, UpdateUserValidation.class})
    private String name;

    @ValidPassword(groups = {SignUpValidation.class, SignInEmailValidation.class, SignInNameValidation.class})
    private String password;

    private List<Integer> topicIds;
}
