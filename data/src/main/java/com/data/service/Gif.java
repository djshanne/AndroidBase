package com.data.service;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Juan Delgado on 3/12/2016.
 */
public interface Gif {

    @GET("{url}")
    Call<ResponseBody> getGif(@Path(value = "url", encoded = true) String url);

}
