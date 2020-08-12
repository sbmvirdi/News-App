package com.shubamvirdi.newsapp.Interfaces;

import com.shubamvirdi.newsapp.ModelClasses.NewsData;
import com.shubamvirdi.newsapp.ModelClasses.SourceHead;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    // api call to get the sources of news
    @GET("sources")
    Call<SourceHead> getAllNewsSources(@Query("apiKey") String API_KEY);

    // api call to get headlines of specific news id
    @GET("top-headlines")
    Call<NewsData> getNewsData(@Query("sources") String sources, @Query("apiKey") String API_KEY);
}
