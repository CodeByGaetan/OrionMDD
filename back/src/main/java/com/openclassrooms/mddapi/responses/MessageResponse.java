package com.openclassrooms.mddapi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    public final String message;
    public Integer codeError;
}
