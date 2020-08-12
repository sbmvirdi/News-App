package com.shubamvirdi.newsapp.ViewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shubamvirdi.newsapp.ModelClasses.SourceHead;
import com.shubamvirdi.newsapp.Repository.NewsRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<SourceHead> sourceHead = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private NewsRepository mRepo;

    public void init(Context context){
        mRepo = NewsRepository.getInstance();
        isLoading.setValue(true);
        mRepo.getTabsData(context,sourcehead ->{
            isLoading.setValue(false);
            sourceHead.setValue(sourcehead);
        });
    }

    public LiveData<SourceHead> getSourceHead() {
        return sourceHead;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}
