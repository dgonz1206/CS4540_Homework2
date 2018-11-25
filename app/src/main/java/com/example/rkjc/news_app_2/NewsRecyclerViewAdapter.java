package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    Context mContext;
    List<NewsItem> mNewsItems;

    public NewsRecyclerViewAdapter(Context context, ArrayList<NewsItem> newItems){
        this.mContext = context;
        this.mNewsItems = newItems;
    }

    @Override
    public NewsRecyclerViewAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.news_item, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewAdapter.NewsViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView description;
        TextView date;
        TextView url;
        TextView author;
        TextView img;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.time);
            url = (TextView) itemView.findViewById(R.id.url);
            author = (TextView) itemView.findViewById(R.id.author);
            img = (TextView) itemView.findViewById(R.id.img);
        }

        void bind(final int listIndex) {
            title.setText(String.format("Title: %s", mNewsItems.get(listIndex).getTitle()));
            description.setText(String.format("Description: %s", mNewsItems.get(listIndex).getDescription()));
            date.setText(String.format("Date: %s", mNewsItems.get(listIndex).getPublished()));
            url.setText(String.format("URL: %s", mNewsItems.get(listIndex).getImg()));
            author.setText(String.format("Author: %s", mNewsItems.get(listIndex).getAuthor()));
            img.setText(String.format("Image: %s", mNewsItems.get(listIndex).getUrl()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String urlString = mNewsItems.get(listIndex).getUrl();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mNewsItems.get(listIndex).getUrl()));
                    intent.putExtra("urlString", urlString);
                    mContext.startActivity(intent);

                }
            });
        }

    }



    void setNews(List<NewsItem> newsItem){
        mNewsItems = newsItem;
        notifyDataSetChanged();
    }



}
