package com.openclassrooms.mddapi.others.exceptions;

public class AuthException extends Exception {

    private Integer codeError;

    public AuthException(String message, Integer codeError) {
        super(message);
        this.codeError = codeError;
    }

    public Integer getCodeError() {
        return this.codeError;
    }
}
