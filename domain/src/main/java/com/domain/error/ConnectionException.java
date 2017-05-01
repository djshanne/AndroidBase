package com.domain.error;

/**
 * Created by Juan Delgado on 5/26/2016.
 */
public class ConnectionException extends Exception{

    public ConnectionException() {
        super(ConnectionException.class.getSimpleName());
    }
}
