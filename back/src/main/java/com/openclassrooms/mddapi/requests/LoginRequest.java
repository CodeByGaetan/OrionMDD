package com.openclassrooms.mddapi.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.openclassrooms.mddapi.validations.groups.SignInEmailValidation;
import com.openclassrooms.mddapi.validations.groups.SignInNotBlanckValidation;

import lombok.Data;

@Data
public class LoginRequest {

    @Email(groups = {SignInEmailValidation.class})
    @NotBlank(groups = {SignInNotBlanckValidation.class})
    private String username;

    @NotBlank(groups = {SignInNotBlanckValidation.class})
    private String password;
}
