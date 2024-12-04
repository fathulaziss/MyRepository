package com.example.myrepository.di;

import android.content.Context;

import com.example.myrepository.data.NewsRepository;
import com.example.myrepository.data.local.room.NewsDao;
import com.example.myrepository.data.local.room.NewsDatabase;
import com.example.myrepository.data.remote.retrofit.ApiConfig;
import com.example.myrepository.data.remote.retrofit.ApiService;
import com.example.myrepository.utils.AppExecutors;

public class Injection {
    public static NewsRepository provideRepository(Context context) {
        ApiService apiService = ApiConfig.getApiService();
        NewsDatabase database = NewsDatabase.getInstance(context);
        NewsDao dao = database.newsDao();
        AppExecutors appExecutors = new AppExecutors();
        return NewsRepository.getInstance(apiService, dao, appExecutors);
    }
}
