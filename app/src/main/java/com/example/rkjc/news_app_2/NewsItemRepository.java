package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsItemRepository {

    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllNewsItems;
    //private ArrayList<NewsItem> urlStuff;

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

    public void impossible(List<NewsItem> newsItem){

    }

    public ArrayList<NewsItem> parseIntoDao(Context context) {
        ArrayList<NewsItem> result = null;
        URL url = NetworkUtils.buildUrl();
        String searchResults = null;
        Log.d("mycode2", "FUUUUUUUUUUCK");

        ArrayList<NewsItem> news = new ArrayList<NewsItem>();
        syncMyShit shit = new syncMyShit(mNewsItemDao);
        apiCall api = new apiCall(mNewsItemDao);
        api.execute(url);
        //result = api.getItem();
        Log.d("mycode2", mNewsItemDao.toString());
        //Log.d("mycode2", result.toString());

        return result;
    }



    private static class apiCall extends AsyncTask<URL, NewsItemDao, String> {
        //URL url = NetworkUtils.buildUrl();
        String searchResults = null;
        //private NewsItemDao mAsyncTaskDao;
        ArrayList<NewsItem> result = new ArrayList<>();
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


//            if(searchResults != null && !searchResults.equals("")){
//                mAsyncTaskDao.clearAll();
//
//                ArrayList<NewsItem> news = JsonUtils.parseNews(searchResults);
//
//                mAsyncTaskDao.insert(news);
//                mAsyncTaskDao.loadAllNewsItems();
//            }
//            else{
            new syncMyShit(mAsyncTaskDao);
//            }
//
            return null;
        }

        //        @Override
//        protected void onPostExecute(String searchResults) {
//            //Log.d("mycode",searchResults);
//
//            if(searchResults != null && !searchResults.equals("")){
//                Log.d("mycode","SOMETHING'S IN HERE");
//
//                //Log.d("mycode","SHIT");
//            }
//
//        }
        private ArrayList<NewsItem> getItem(){
            //Log.d("mycode", result.toString());

            return news;
        }
    }


    private static class syncMyShit extends AsyncTask<List<NewsItem>, Void, Void> {

        private NewsItemDao mAsyncTaskDao;

        syncMyShit(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<NewsItem>... params) {

            //mAsyncTaskDao.insert(news);

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
            //Log.d("TAB", mAsyncTaskDao.toString());
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
            Log.d("mycode", "deleting word: " + params[0].toString());
            mAsyncTaskDao.clearAll();
            return null;
        }
    }
}
