package com.example.rkjc.news_app_2;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {NewsItem.class}, version = 1, exportSchema = false)
public abstract class NewsItemDatabase extends RoomDatabase {

    public abstract NewsItemDao newsItemDao();

    private static volatile NewsItemDatabase INSTANCE;

    static NewsItemDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsItemDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsItemDatabase.class, "word_database")
                            .build();                }
            }
        }
        return INSTANCE;
    }


//    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
//
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//            // If you want to keep the data through app restarts,
//            // comment out the following line.
//            new PopulateDbAsync(INSTANCE).execute();
//        }
//    };
//
//    /**
//     * Populate the database in the background.
//     * If you want to start with more words, just add them.
//     */
//    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
//
//        private final NewsItemDao mDao;
//
//        PopulateDbAsync(NewsItemDatabase db) {
//            mDao = db.newsItemDao();
//        }
//
//        @Override
//        protected Void doInBackground(final Void... params) {
//            // Start the app with a clean database every time.
//            // Not needed if you only populate on creation.
//            mDao.clearAll();
//
//            //word = new Word("World");
//            //mDao.insert(word);
//            return null;
//        }
//    }
}
