package com.example.maximus.vitaminreminder.network;

import com.example.maximus.vitaminreminder.data.newsdata.NewsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("everything")
    Call<NewsListResponse> getEverything(@Query("q") String query, @Query("apiKey") String apiKey);


}
