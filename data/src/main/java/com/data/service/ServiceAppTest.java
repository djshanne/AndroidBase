package com.data.service;


import com.data.BuildConfig;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Eventss;
import com.model.bean.characters.Result;
import com.model.bean.characters.comics.Comics;
import com.model.bean.characters.comics.Creatorss;
import com.model.bean.characters.comics.Seriess;
import com.model.bean.common.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Juan Delgado on 3/12/2016.
 */

public interface ServiceAppTest {

    @POST("/api/register/")
    public Call<User> register(@Body User requestRegister);
}
