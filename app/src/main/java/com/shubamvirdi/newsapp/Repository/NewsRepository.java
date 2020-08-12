package com.shubamvirdi.newsapp.Repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.shubamvirdi.newsapp.Interfaces.ApiInterface;
import com.shubamvirdi.newsapp.Interfaces.LoadData;
import com.shubamvirdi.newsapp.ModelClasses.NewsData;
import com.shubamvirdi.newsapp.ModelClasses.Source;
import com.shubamvirdi.newsapp.ModelClasses.SourceHead;
import com.shubamvirdi.newsapp.R;
import com.shubamvirdi.newsapp.SingletonClasses.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsRepository {

    public static NewsRepository instance;

    public static NewsRepository getInstance() {
        if (instance == null) {
            instance = new NewsRepository();
        }

        return instance;
    }

    // function to get news from a particular news id
    public void getNewsByNewsId(Context context,String newsId, LoadData<NewsData> load){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<NewsData> call = apiInterface.getNewsData(newsId,context.getResources().getString(R.string.API_KEY));
        call.enqueue(new Callback<NewsData>() {
            @Override
            public void onResponse(@NonNull Call<NewsData> call, @NonNull Response<NewsData> response) {
                if (response.isSuccessful()){
                    load.onDataLoaded(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsData> call,@NonNull Throwable t) {
            Log.e("getNewsByNewsId",t.getMessage()+"");
            }
        });
    }

    // function to get all the sources of news from news api to generate the tabs
    public void getTabsData(Context context,LoadData<SourceHead> load){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SourceHead> call = apiInterface.getAllNewsSources(context.getResources().getString(R.string.API_KEY));
        call.enqueue(new Callback<SourceHead>() {
            @Override
            public void onResponse(@NonNull Call<SourceHead> call, @NonNull Response<SourceHead> response) {
                if (response.isSuccessful()) {
                  load.onDataLoaded(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SourceHead> call, @NonNull Throwable t) {
                Log.e("getTabsData", t.getMessage()+"");
            }
        });
    }
}
