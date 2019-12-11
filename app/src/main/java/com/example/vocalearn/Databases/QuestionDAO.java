package com.example.vocalearn.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vocalearn.Entity.Question;
import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.SqliteOpenHelper.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static QuestionDAO instance;
    Cursor c = null;

    private QuestionDAO(Context context)
    {
        this.openHelper = new DatabaseOpenHelper(context);
    }
    public static QuestionDAO getInstance(Context context)
    {
        if(instance==null){
            instance = new QuestionDAO(context);
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
    public List<Question> getAllQuestion()
    {
        List<Question> questionList = new ArrayList<>();
        db = openHelper.getReadableDatabase();
        c= db.rawQuery("select * from TuDien",null);
        if (c.moveToFirst()) {
            do {

            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
