package com.example.vocalearn.Interface;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vocalearn.Entity.Words;

import java.util.List;
@Dao
public interface WordsDAO {
    @Insert
    void insert(Words words);

    @Update
    void update(Words words);

    @Delete
    void delete(Words words);

    @Query("DELETE FROM Words_table")
    void deleteAllWords();

    @Query("SELECT * FROM words_table")
    LiveData<List<Words>> getAllWord();
}
