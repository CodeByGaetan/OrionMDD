package com.openclassrooms.mddapi.others.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthErrorResponse {
    public String message;
    public Integer codeError;
}
