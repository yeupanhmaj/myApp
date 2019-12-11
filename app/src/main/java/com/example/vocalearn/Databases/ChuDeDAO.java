package com.example.vocalearn.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vocalearn.Entity.ChuDe;
import com.example.vocalearn.SqliteOpenHelper.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ChuDeDAO {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static ChuDeDAO instance;
    Cursor c = null;

    private ChuDeDAO(Context context)
    {
        this.openHelper = new DatabaseOpenHelper(context);
    }
    public static ChuDeDAO getInstance(Context context)
    {
        if(instance==null){
            instance = new ChuDeDAO(context);
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
    public List<ChuDe> getAllChuDe()
    {
        List<ChuDe> chudeList = new ArrayList<>();
        db = openHelper.getReadableDatabase();
        c= db.rawQuery("select * from chude",null);
        if (c.moveToFirst()) {
            do {
                ChuDe temp = new ChuDe();
                temp.setIDChuDe(c.getString(0));
                temp.setTenChuDe(c.getString(1));
                chudeList.add(temp);
            } while (c.moveToNext());
        }

        c.close();
        return chudeList;
    }
}
