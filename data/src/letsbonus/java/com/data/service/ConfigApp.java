package com.data.service;


import com.model.bean.Config;
import com.model.bean.Environment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Juan Delgado on 3/12/2016.
 */
public interface ConfigApp {

    @GET("/s/lu1rcuk1v612sjz/companyenvironmentsselector.json?dl=0")
    Call<List<Environment>> getConfig(@Query("raw") String raw);

    @GET("/s/lu1rcuk1v612sjz/companyenvironmentsselector.json?dl=0")
    Call<Config> getConfigFile(@Query("raw") String raw);

}
