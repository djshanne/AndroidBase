package com.data.service.error;

/**
 * Created by juan.delgado on 31/05/2016.
 */
public class BadRequestException extends Exception {
    public BadRequestException(String msg) {
        super(msg);
    }

}
