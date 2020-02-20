package com.example.fisrtminiproject.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageAPI {
    private static Retrofit mRetrofit;
    public static Service getService(){
        return getmRetrofit().create(Service.class);
    }
    private static Retrofit getmRetrofit(){
        if (mRetrofit == null)
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://api.unsplash.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return mRetrofit;
    }
}
