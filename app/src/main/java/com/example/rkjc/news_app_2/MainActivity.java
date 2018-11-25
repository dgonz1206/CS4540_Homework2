package com.example.rkjc.news_app_2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mAdapter;
    private ArrayList<NewsItem> newsItems = new ArrayList<>();
    private NewsItemViewModel mNewsItemViewModel;

    private NewsItemDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mRecyclerView = (RecyclerView)findViewById(R.id.news_recyclerview);
        mAdapter = new NewsRecyclerViewAdapter(this, newsItems);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);


        //mDb = NewsItemDatabase.getDatabase((getApplicationContext()));
        //Log.d("mycode2", mDb.);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if(menuItemThatWasSelected == R.id.action_search){
            //URL searchUrl = NetworkUtils.buildUrl();
            //queryTask task = new queryTask();
            //task.execute(searchUrl);
            NewsItemRepository nip = new NewsItemRepository(getApplication());
            mNewsItemViewModel.loadAllNewsItems().observe(this, new Observer<List<NewsItem>>() {
                @Override
                public void onChanged(@Nullable final List<NewsItem> words) {
                    // Update the cached copy of the words in the adapter.
                    mAdapter.setWords(words);
                    //Log.d("mycode", words.toString());
                }
            });
            ArrayList<NewsItem> result = new ArrayList<>();

            //if(mAdapter.getItemCount()==0){
            result = nip.parseIntoDao(this);
            //}
            //ArrayList<NewsItem> ls = nip.parseIntoDao(this);
            //Log.d("mycode", mAdapter.getItemCount()+"");

            //Log.d("mycode", result.toString());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

//    class queryTask extends AsyncTask<URL, Void, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            mProgressBar.setVisibility(View.VISIBLE);
//
//        }
//
//        @Override
//        protected String doInBackground(URL... urls) {
//
//            URL searchUrl = urls[0];
//            String searchResults = null;
//
//            try {
//                searchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return searchResults;
//        }
//
//        @Override
//        protected void onPostExecute(String searchResults) {
//            if(searchResults != null && !searchResults.equals("")){
//                mProgressBar.setVisibility(View.GONE);
//                ArrayList<NewsItem> news = JsonUtils.parseNews(searchResults);
//                mAdapter.mNewsItems.addAll(news);
//                mAdapter.notifyDataSetChanged();
//
//            }
//        }
//    }



}
