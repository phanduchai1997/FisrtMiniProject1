package com.example.fisrtminiproject.api;

import com.example.fisrtminiproject.model.Image;
import com.example.fisrtminiproject.model.Image1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("photos")
    Call<List<Image>> getAllImage(@Query("page") int page);
    @GET("search/photos")
    Call<Image1> findImage(@Query("page") int page, @Query("query") String query);
}
