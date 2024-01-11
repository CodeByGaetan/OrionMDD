package com.openclassrooms.mddapi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.openclassrooms.mddapi.validations.groups.SignUpValidation;
import com.openclassrooms.mddapi.validations.groups.UpdateUserValidation;
import com.openclassrooms.mddapi.validations.password.ValidPassword;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    
    @Email(groups = {SignUpValidation.class, UpdateUserValidation.class})
    @NotBlank(groups = {SignUpValidation.class, UpdateUserValidation.class})
    private String email;

    @NotBlank(groups = {SignUpValidation.class, UpdateUserValidation.class})
    private String name;

    @ValidPassword(groups = {SignUpValidation.class})
    private String password;
}
