package com.example.myrepository.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<ArticlesItem> articles;

    @SerializedName("status")
    private String status;

    public int getTotalResults(){
        return totalResults;
    }

    public List<ArticlesItem> getArticles(){
        return articles;
    }

    public String getStatus(){
        return status;
    }
}
