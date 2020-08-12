package com.shubamvirdi.newsapp.Interfaces;

import com.shubamvirdi.newsapp.ModelClasses.NewsData;
import com.shubamvirdi.newsapp.ModelClasses.SourceHead;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("sources")
    Call<SourceHead> getAllNewsSources(@Query("apiKey") String API_KEY);

    //https://newsapi.org/v2/top-headlines?sources=cnn&apiKey=1c6b7923dc2b4f0c9a7ee509f1647736

    @GET("top-headlines")
    Call<NewsData> getNewsData(@Query("sources") String sources,@Query("apiKey") String API_KEY);
}
