package com.example.vocalearn.Databases;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.vocalearn.Interface.WordsDAO;
import com.example.vocalearn.Entity.Words;

@Database(entities = Words.class, version = 1,exportSchema = false)
public abstract class WordsDatabase extends RoomDatabase {
    private static WordsDatabase instance;

    public abstract WordsDAO wordsDAO();

    public static synchronized WordsDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WordsDatabase.class,"Words_database")
                    .fallbackToDestructiveMigration()//tránh crash
                    .addCallback(romCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback romCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private WordsDAO wordsDAO;

        private PopulateDbAsyncTask(WordsDatabase db)
        {
            wordsDAO= db.wordsDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordsDAO.insert(new Words("abclks","phat am 1","Nghia 1","Ghi chu 1","status 1"));
            wordsDAO.insert(new Words("tu 2","phat am 2","Nghia 2","Ghi chu 2","status 2"));
            wordsDAO.insert(new Words("tu 3","phat am 3","Nghia 3","Ghi chu 3","status 3"));
            wordsDAO.insert(new Words("tu 4","phat am 4","Nghia 4","Ghi chu 4","status 4"));
            return null;
        }
    }
}
