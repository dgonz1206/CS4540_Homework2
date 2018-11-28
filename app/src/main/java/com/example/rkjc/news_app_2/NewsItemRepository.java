package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsItemRepository {

    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllNewsItems;

    NewsItemRepository(Application application) {
        NewsItemDatabase db = NewsItemDatabase.getDatabase(application);
        mNewsItemDao = db.newsItemDao();
        mAllNewsItems = mNewsItemDao.loadAllNewsItems();

    }

    LiveData<List<NewsItem>> loadAllNewsItems() {
        return mAllNewsItems;
    }


    public void insert (List<NewsItem> newsItem) {
        new insertAsyncTask(mNewsItemDao).execute(newsItem);
    }

    public void clearAll(List<NewsItem> newsItem){
        new clearAllAsyncTask(mNewsItemDao).execute(newsItem);
    }


    public ArrayList<NewsItem> syncDbAPI(Context context) {
        ArrayList<NewsItem> result = null;
        URL url = NetworkUtils.buildUrl();
        apiCall api = new apiCall(mNewsItemDao);
        api.execute(url);

        return result;
    }

    public void fetchDatabaseNews() {
        new databaseASync(mNewsItemDao);
    }

    private static class apiCall extends AsyncTask<URL, NewsItemDao, String> {
        String searchResults = null;
        ArrayList<NewsItem> news= new ArrayList<>();

        private NewsItemDao mAsyncTaskDao;

        apiCall(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected String doInBackground(URL... urls) {
            try {
                searchResults = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }


            if(searchResults != null && !searchResults.equals("")){

                mAsyncTaskDao.clearAll();
                ArrayList<NewsItem> news = JsonUtils.parseNews(searchResults);
                mAsyncTaskDao.insert(news);
                mAsyncTaskDao.loadAllNewsItems();
            }
            return null;
        }


        private ArrayList<NewsItem> getItem(){
            return news;
        }
    }


    private static class databaseASync extends AsyncTask<List<NewsItem>, Void, Void> {

        private NewsItemDao mAsyncTaskDao;

        databaseASync(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<NewsItem>... params) {

            mAsyncTaskDao.loadAllNewsItems();
            return null;
        }
    }


    private static class insertAsyncTask extends AsyncTask<List<NewsItem>, Void, Void> {

        private NewsItemDao mAsyncTaskDao;

        insertAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<NewsItem>... params) {

            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class clearAllAsyncTask extends AsyncTask<List<NewsItem>, Void, Void> {
        private NewsItemDao mAsyncTaskDao;
        clearAllAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<NewsItem>... params) {
            mAsyncTaskDao.clearAll();
            return null;
        }
    }
}
