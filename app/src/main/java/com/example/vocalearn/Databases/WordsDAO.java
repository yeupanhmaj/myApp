package com.example.vocalearn.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.SqliteOpenHelper.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class WordsDAO {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static WordsDAO instance;
    Cursor c = null;

    private WordsDAO(Context context)
    {
        this.openHelper = new DatabaseOpenHelper(context);
    }
    public static WordsDAO getInstance(Context context)
    {
        if(instance==null){
            instance = new WordsDAO(context);
        }
        return instance;

    }

    public void openDB()
    {
        this.db = openHelper.getWritableDatabase();
    }
    public void closeDB()
    {
        if(db!=null)
        {
            this.db.close();
        }
    }
    public List<Words> getAllWords()
    {
        List<Words> questionList = new ArrayList<>();
        db = openHelper.getReadableDatabase();
        c= db.rawQuery("select * from TuDien",null);
        if (c.moveToFirst()) {
            do {
                Words temp = new Words();
                temp.setTu(c.getString(0));
                temp.setPhatAm(c.getString(1));
                temp.setNghia(c.getString(2));
                temp.setGhichu(c.getString(3));
                temp.setStatus(c.getString(4));
                questionList.add(temp);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
