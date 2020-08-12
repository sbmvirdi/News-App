package com.shubamvirdi.newsapp.ViewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shubamvirdi.newsapp.ModelClasses.NewsData;
import com.shubamvirdi.newsapp.Repository.NewsRepository;

import java.util.List;

public class DynamicFragmentViewModel extends ViewModel {
    private MutableLiveData<NewsData> newsData = new MutableLiveData<>();
    private NewsRepository mRepo;
    public void init(Context context,String newsId){
        mRepo = NewsRepository.getInstance();
        mRepo.getNewsByNewsId(context,newsId,newsdata->{
            newsData.setValue(newsdata);
        });
    }

    public LiveData<NewsData> getNewsData() {
        return newsData;
    }
}
