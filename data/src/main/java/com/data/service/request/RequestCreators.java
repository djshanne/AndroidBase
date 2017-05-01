package com.data.service.request;

/**
 * Created by Juan Delgado on 5/26/2016.
 */
public class RequestCreators extends RequestList {

    private int id;

    public RequestCreators(int id) {
        this.id = id;
    }

    public RequestCreators() {
    }

    public int getId() {
        return id;
    }
}
