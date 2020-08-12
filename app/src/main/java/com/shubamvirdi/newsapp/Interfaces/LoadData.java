package com.shubamvirdi.newsapp.Interfaces;

// interface used to load data of any type
public interface LoadData<T> {
    void onDataLoaded(T load);
}
