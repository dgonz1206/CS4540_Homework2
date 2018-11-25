package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

    private NewsItemRepository mRepository;

    private LiveData<List<NewsItem>> mAllWords;

    public NewsItemViewModel(Application application) {
        super(application);
        mRepository = new NewsItemRepository(application);
        mAllWords = mRepository.loadAllNewsItems();
    }

    LiveData<List<NewsItem>> loadAllNewsItems() { return mAllWords; }

    public void insert(List<NewsItem> newsItem) { mRepository.insert(newsItem); }

}
