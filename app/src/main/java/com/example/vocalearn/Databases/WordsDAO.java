package com.example.vocalearn.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vocalearn.Entity.Option;
import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.SqliteOpenHelper.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class WordsDAO {
    private String TABLE_NAME = "TuDien";
    public static final String Tu = "Tu";
    public static final String PhatAm = "PhatAm";
    public static final String Nghia = "Nghia";
    public static final String Ghichu = "Ghichu";
    public static final String ChuDe = "ChuDe";
    public static final String Hard = "Hard";
    public static final String Favorite = "Favorite";
    public static final String Learned = "Learned";
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
                temp.setChude(c.getString(4));
                temp.setHard(c.getInt(5));
                temp.setFavorite(c.getInt(6));
                temp.setLearned(c.getInt(7));
                questionList.add(temp);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
    public Words getRandom(String ChuDe)
    {
        Words questionList = new Words();
        String query ="SELECT * FROM TuDien WHERE ChuDe="+"'"+ ChuDe +"'" +" ORDER BY random() LIMIT 1";
        db = openHelper.getReadableDatabase();
        c= db.rawQuery(query,null);
        if (c.moveToFirst()) {
            do {
                Words temp = new Words();
                temp.setTu(c.getString(0));
                temp.setPhatAm(c.getString(1));
                temp.setNghia(c.getString(2));
                temp.setGhichu(c.getString(3));
                temp.setChude(c.getString(4));
                temp.setHard(c.getInt(5));
                temp.setFavorite(c.getInt(6));
                temp.setLearned(c.getInt(7));
                questionList=(temp);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
    public Words getRandom(String ChuDe,int fav)
    {
        Words questionList = new Words();
        String query ="SELECT * FROM TuDien WHERE ChuDe="+"'"+ ChuDe +"' and Favorite = "+ fav + " ORDER BY random() LIMIT 1";
        db = openHelper.getReadableDatabase();
        c= db.rawQuery(query,null);
        if (c.moveToFirst()) {
            do {
                Words temp = new Words();
                temp.setTu(c.getString(0));
                temp.setPhatAm(c.getString(1));
                temp.setNghia(c.getString(2));
                temp.setGhichu(c.getString(3));
                temp.setChude(c.getString(4));
                temp.setHard(c.getInt(5));
                temp.setFavorite(c.getInt(6));
                temp.setLearned(c.getInt(7));
                questionList=(temp);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
    public Words getWord(String KEY)
    {
        Words questionList = new Words();
        String query ="SELECT * FROM TuDien WHERE Tu="+"'"+ KEY +"'";
        db = openHelper.getReadableDatabase();
        c= db.rawQuery(query,null);
        if (c.moveToFirst()) {
            do {
                Words temp = new Words();
                temp.setTu(c.getString(0));
                temp.setPhatAm(c.getString(1));
                temp.setNghia(c.getString(2));
                temp.setGhichu(c.getString(3));
                temp.setChude(c.getString(4));
                temp.setHard(c.getInt(5));
                temp.setFavorite(c.getInt(6));
                temp.setLearned(c.getInt(7));
                questionList=(temp);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
    public List<Words> getAllWordsFromSub(String key)
    {
        List<Words> questionList = new ArrayList<>();
        db = openHelper.getReadableDatabase();
        String query ="SELECT *\n" +
                "FROM TuDien\n" +
                "WHERE tu LIKE '%"+key+"%';";
        c= db.rawQuery(query,null);
        if (c.moveToFirst()) {
            do {
                Words temp = new Words();
                temp.setTu(c.getString(0));
                temp.setPhatAm(c.getString(1));
                temp.setNghia(c.getString(2));
                temp.setGhichu(c.getString(3));
                temp.setChude(c.getString(4));
                temp.setHard(c.getInt(5));
                temp.setFavorite(c.getInt(6));
                temp.setLearned(c.getInt(7));
                questionList.add(temp);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
    public void updateWord(Words words,int Fav)
    {
        db = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Tu,words.getTu());
        cv.put(PhatAm,words.getPhatAm());
        cv.put(Nghia,words.getNghia());
        cv.put(Ghichu,words.getGhichu());
        cv.put(ChuDe,words.getChude());
        cv.put(Hard,words.getHard());
        cv.put(Favorite,Fav);
        cv.put(Learned,words.getLearned());
        db.update(TABLE_NAME,cv,"Tu='"+words.getTu()+"'",null);
        db.close();
    }
    public void insertWord(Words words)
    {
        db = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Tu,words.getTu());
        cv.put(PhatAm,words.getPhatAm());
        cv.put(Nghia,words.getNghia());
        cv.put(Ghichu,words.getGhichu());
        cv.put(ChuDe,words.getChude());
        cv.put(Hard,0);
        cv.put(Favorite,1);
        cv.put(Learned,0);
        db.insert(TABLE_NAME,null,cv);
        db.close();
    }
    public void deleteAt(String key)
    {
        db = openHelper.getWritableDatabase();
        db.delete(TABLE_NAME,"Tu='"+key+"'",null);
    }
    public  int countFav(String ChuDe,int fav)
    {
        int questionList =0;
        String query ="select count(tu) from TuDien WHERE ChuDe='"+ChuDe+"' and Favorite = "+fav;
        db = openHelper.getReadableDatabase();
        c= db.rawQuery(query,null);
        if (c.moveToFirst()) {
            do {
                questionList = c.getInt(0);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
    public Option getOption(String KEY,int fav)
    {
        Option questionList = new Option();
        String query ="SELECT * FROM TuDien WHERE ChuDe="+"'"+ KEY +"' and Favorite = "+ fav + " ORDER BY random() LIMIT 1";
        db = openHelper.getReadableDatabase();
        c= db.rawQuery(query,null);
        if (c.moveToFirst()) {
            do {
                questionList.setWord(c.getString(0));
                questionList.setMean(c.getString(2));
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
