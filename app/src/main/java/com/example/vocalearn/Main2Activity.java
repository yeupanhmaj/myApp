package com.example.vocalearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.vocalearn.CustomAdapter.WordsAdapter;
import com.example.vocalearn.Databases.WordsDAO;
import com.example.vocalearn.Entity.Words;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WordsDAO dbAccess;
    private List<Words> mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.rclWords);
        dbAccess = dbAccess.getInstance(getApplicationContext());
        dbAccess.openDB();

        mylist = dbAccess.getAllWords();

        dbAccess.closeDB();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final WordsAdapter wordsAdapter = new WordsAdapter();
        wordsAdapter.setWords(mylist);
        recyclerView.setAdapter(wordsAdapter);


    }
}
