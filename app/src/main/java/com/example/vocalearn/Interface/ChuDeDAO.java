package com.example.vocalearn.Interface;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vocalearn.Entity.ChuDe;

public interface ChuDeDAO {
    @Insert
     void insert(ChuDe chuDe);

    @Update
     void update(ChuDe chuDe);

    @Delete
     void delete(ChuDe chuDe);

    @Query("DELETE FROM Chu_De")
    void deleteAllChuDe();


}
