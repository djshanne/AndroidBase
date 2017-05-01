package com.domain.error;

/**
 * Created by Juan Delgado on 5/26/2016.
 */
public class SignUpException extends Exception{

    public SignUpException(String msg) {
        super(SignUpException.class.getSimpleName());
    }
}
