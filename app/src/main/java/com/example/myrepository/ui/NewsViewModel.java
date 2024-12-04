package com.example.myrepository.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myrepository.data.NewsRepository;
import com.example.myrepository.data.Result;
import com.example.myrepository.data.local.entity.NewsEntity;

import java.util.List;

public class NewsViewModel extends ViewModel {
    private final NewsRepository newsRepository;

    public NewsViewModel(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public LiveData<Result<List<NewsEntity>>> getHeadlineNews() {
        return newsRepository.getHeadlineNews();
    }

}