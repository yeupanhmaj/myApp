package com.example.vocalearn.Repositorys;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.vocalearn.Databases.WordsDatabase;
import com.example.vocalearn.Interface.WordsDAO;
import com.example.vocalearn.Entity.Words;

import java.util.List;

public class WordReposiroty {
    private WordsDAO wordsDAO;
    private LiveData<List<Words>> allWords;
    public WordReposiroty(Application application)
    {
        WordsDatabase wordsDatabase = WordsDatabase.getInstance(application);
        wordsDAO = wordsDatabase.wordsDAO();
        allWords = wordsDAO.getAllWord();
    }

    public void insert(Words words){
        new InsertWordAsyncTask(wordsDAO).execute(words);
    }


    public void update(Words words){
        new UdapteWordAsyncTask(wordsDAO).execute(words);
    }


    public void delete(Words words){
        new DeleteWordAsyncTask(wordsDAO).execute(words);
    }


    public void deleteAllWords(){
        new DeleteAllWordAsyncTask(wordsDAO).execute();
    }


    public LiveData<List<Words>> getAllWord(){
        return allWords;
    }

    private static class InsertWordAsyncTask extends AsyncTask<Words,Void, Void>
    {
        private WordsDAO wordsDAO;

        private InsertWordAsyncTask(WordsDAO wordsDao)
        {
            this.wordsDAO = wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            wordsDAO.insert(words[0]);
            return null;
        }
    }
    private static class UdapteWordAsyncTask extends AsyncTask<Words,Void, Void>
    {
        private WordsDAO wordsDAO;

        private UdapteWordAsyncTask(WordsDAO wordsDao)
        {
            this.wordsDAO = wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            wordsDAO.update(words[0]);
            return null;
        }
    }
    private static class DeleteWordAsyncTask extends AsyncTask<Words,Void, Void>
    {
        private WordsDAO wordsDAO;

        private DeleteWordAsyncTask(WordsDAO wordsDao)
        {
            this.wordsDAO = wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            wordsDAO.delete(words[0]);
            return null;
        }
    }
    private static class DeleteAllWordAsyncTask extends AsyncTask<Words,Void, Void>
    {
        private WordsDAO wordsDAO;

        private DeleteAllWordAsyncTask(WordsDAO wordsDao)
        {
            this.wordsDAO = wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            wordsDAO.deleteAllWords();
            return null;
        }
    }
}
