package com.shubamvirdi.newsapp.ModelClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// model class for source head
public class SourceHead {

    @SerializedName("status")
    private String status;

    @SerializedName("sources")
    List<Source> sources;

    public String getStatus() {
        return status;
    }

    public List<Source> getSources() {
        return sources;
    }
}
