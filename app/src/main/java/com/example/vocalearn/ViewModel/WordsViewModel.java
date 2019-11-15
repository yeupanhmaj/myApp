package com.example.vocalearn.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.Repositorys.WordReposiroty;

import java.util.List;

public class WordsViewModel extends AndroidViewModel {
    private WordReposiroty reposiroty;
    private LiveData<List<Words>> allWword;

    public WordsViewModel(@NonNull Application application) {
        super(application);
        reposiroty = new WordReposiroty(application);
        allWword = reposiroty.getAllWord();
    }
    public void insert(Words words)
    {
        reposiroty.insert(words);
    }
    public void update(Words words)
    {
        reposiroty.update(words);
    }
    public void delete(Words words)
    {
        reposiroty.delete(words);
    }
    public void deleteAllWords(Words words)
    {
        reposiroty.deleteAllWords();
    }
    public LiveData<List<Words>> getAllWword()
    {
        return allWword;
    }
}
