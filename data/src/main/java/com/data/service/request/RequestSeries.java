package com.data.service.request;

/**
 * Created by Juan Delgado on 5/26/2016.
 */
public class RequestSeries extends RequestList {

    private int id;

    public RequestSeries(int id) {
        this.id = id;
    }

    public RequestSeries() {
    }

    public int getId() {
        return id;
    }


}
