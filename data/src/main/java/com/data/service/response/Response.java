package com.data.service.response;

import android.net.Uri;

import com.data.service.request.Request;

/**
 * Created by juan.delgado on 17/03/2017.
 */

public class Response {

    private Uri request;

    public Response(Uri request) {
        this.request = request;
    }

    public Uri getRequest() {
        return request;
    }
}
