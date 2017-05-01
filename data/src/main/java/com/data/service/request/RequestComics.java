package com.data.service.request;

/**
 * Created by Juan Delgado on 5/26/2016.
 */
public class RequestComics extends RequestList {
    private int id;

    public RequestComics(int id) {
        this.id = id;
    }

    public RequestComics() {
    }

    public int getId() {
        return id;
    }
}
