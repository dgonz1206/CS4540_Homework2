package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

//public class NewsItem {
//    private String title;
//    private String description;
//    private String url;
//    private String author;
//    private String img;
//    private String published;
//
//    public NewsItem(String title, String description, String url, String author, String img, String published) {
//        this.title = title;
//        this.description = description;
//        this.url = url;
//        this.author = author;
//        this.img = img;
//        this.published = published;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }
//
//    public String getPublished() {
//        return published;
//    }
//
//    public void setPublished(String published) {
//        this.published = published;
//    }
//
//    @Override
//    public String toString(){
//        return getTitle() +"\n" + getDescription() +"\n" + getUrl() +"\n" + getAuthor() +"\n" + getImg() +"\n" + getPublished();
//    }
//}
    @Entity(tableName = "news_item")
    public class NewsItem {
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "NewsItem")
        private int id;

        @NonNull
        private String title;
        private String description;
        private String url;
        private String author;
        private String img;
        private String published;

        //@ColumnInfo(name = "word")
        public NewsItem(String title, String description, String url, String author, String img, String published, int id) {
            this.title = title;
            this.description = description;
            this.url = url;
            this.author = author;
            this.img = img;
            this.published = published;
            this.id = id;
        }

        @Ignore
        public NewsItem(String title, String description, String url, String author, String img, String published) {
            this.title = title;
            this.description = description;
            this.url = url;
            this.author = author;
            this.img = img;
            this.published = published;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getUrl() {
            return url;
        }

        public String getAuthor() {
            return author;
        }

        public String getImg() {
            return img;
        }

        public String getPublished() {
            return published;
        }

        public int getId() {
        return id;
    }

        public void setId(int id){
            this.id = id;
        }

}






//    @Database(entities = {NewsItem.class}, version = 1)
//    public abstract class NewsItemDatabase extends RoomDatabase {
//
//        public abstract NewsItemDao newsItemDao();
//
//        private static volatile NewsItemDatabase INSTANCE;
//
//        static WordRoomDatabase getDatabase(final Context context) {
//            if (INSTANCE == null) {
//                synchronized (WordRoomDatabase.class) {
//                    if (INSTANCE == null) {
//                        // Create database here
//                    }
//                }
//            }
//            return INSTANCE;
//        }
//
//    }

