package com.data.service;


import com.data.BuildConfig;
import com.model.bean.BaseResponse;
import com.model.bean.characters.Data;
import com.model.bean.characters.Eventss;
import com.model.bean.characters.Result;
import com.model.bean.characters.comics.Comics;
import com.model.bean.characters.comics.Creatorss;
import com.model.bean.characters.comics.Seriess;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Juan Delgado on 3/12/2016.
 */

public interface ServiceApp {

    @GET(BuildConfig.API_VERSION + "/public/characters")
    public Call<BaseResponse<Data<Result>>> getCharacters(@Query("apikey") String apikey, @Query("hash") String hash, @Query("ts") String ts, @Query("offset") int offset);

    @GET(BuildConfig.API_VERSION + "/public/characters/{id}")
    public Call<BaseResponse<Data<Result>>> getCharacter(@Path("id") Integer id, @Query("apikey") String apikey, @Query("hash") String hash, @Query("ts") String ts, @Query("offset") int offset);

    @GET(BuildConfig.API_VERSION + "/public/characters/{id}/{section}")
    public Call<BaseResponse<Data<Result>>> getCharacterSection(@Path("id") Integer id,@Path("section") String section, @Query("apikey") String apikey, @Query("hash") String hash, @Query("ts") String ts, @Query("offset") int offset);

    @GET(BuildConfig.API_VERSION + "/public/comics")
    public Call<BaseResponse<Data<Comics>>> getComics(@Query("apikey") String apikey, @Query("hash") String hash, @Query("ts") String ts, @Query("offset") int offset);

    @GET(BuildConfig.API_VERSION + "/public/comics/{id}")
    public Call<BaseResponse<Data<Result>>> getComic(@Path("id") Integer id, @Query("apikey") String apikey, @Query("hash") String hash, @Query("ts") String ts, @Query("offset") int offset);

    @GET(BuildConfig.API_VERSION + "/public/creators")
    public Call<BaseResponse<Data<Creatorss>>> getCreators(@Query("apikey") String apikey, @Query("hash") String hash, @Query("ts") String ts, @Query("offset") int offset);

    @GET(BuildConfig.API_VERSION + "/public/creators/{id}")
    public Call<BaseResponse<Data<Result>>> getCreator(@Path("id") Integer id, @Query("apikey") String apikey, @Query("hash") String hash, @Query("ts") String ts, @Query("offset") int offset);

    @GET(BuildConfig.API_VERSION + "/public/events")
    public Call<BaseResponse<Data<Eventss>>> getEvents(@Query("apikey") String apikey, @Query("hash") String hash, @Query("ts") String ts, @Query("offset") int offset);

    @GET(BuildConfig.API_VERSION + "/public/events/{id}")
    public Call<BaseResponse<Data<Result>>> getEvent(@Path("id") Integer id, @Query("apikey") String apikey, @Query("hash") String hash, @Query("ts") String ts, @Query("offset") int offset);

    @GET(BuildConfig.API_VERSION + "/public/series")
    public Call<BaseResponse<Data<Seriess>>> getSeries(@Query("apikey") String apikey, @Query("hash") String hash, @Query("ts") String ts, @Query("offset") int offset);

    @GET(BuildConfig.API_VERSION + "/public/series/{id}")
    public Call<BaseResponse<Data<Seriess>>> getSerie(@Path("id") Integer id, @Query("apikey") String apikey, @Query("hash") String hash, @Query("ts") String ts, @Query("offset") int offset);

}
