package com.shubamvirdi.newsapp.ModelClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsData {
    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private long totalResults;

    @SerializedName("articles")
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
