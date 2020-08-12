package com.shubamvirdi.newsapp.ModelClasses;

import com.google.gson.annotations.SerializedName;

// model class of source of news
public class Source {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
