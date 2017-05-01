package com.data.service.request;

/**
 * Created by Juan Delgado on 5/26/2016.
 */
public class RequestEvents extends RequestList {

    private int id;

    public RequestEvents(int id) {
        this.id = id;
    }

    public RequestEvents() {
    }

    public int getId() {
        return id;
    }
}
