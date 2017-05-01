package com.data.service;

import com.data.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Juan Delgado on 2/21/2016.
 */
public class Cloud {
    private static ServiceApp service;
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private static RequestInterceptor requestInterceptor = new RequestInterceptor();

    public static void initAppService(String environment) {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(requestInterceptor)
                .addNetworkInterceptor(requestInterceptor)
                .addInterceptor(interceptor)
                .readTimeout(160, TimeUnit.MINUTES)
                .writeTimeout(160, TimeUnit.MINUTES)
                .connectTimeout(160, TimeUnit.MINUTES).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(environment)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(ServiceApp.class);
    }

    private static class RequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request.newBuilder()
                    .method(request.method(), request.body())
                    .build();
            return chain.proceed(request);
        }
    }

    public static ServiceApp getApiApp() throws Exception {
        if (service == null) {
            initAppService(BuildConfig.URL_BASE_API);
        }
        return service;
    }

}


