package com.domain.error;

/**
 * Created by Juan Delgado on 5/26/2016.
 */
public class ExecutingTaskException extends Exception{

    public ExecutingTaskException() {
        super(ExecutingTaskException.class.getSimpleName());
    }
}
