package com.example.vocalearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.vocalearn.CustomAdapter.WordsAdapter;
import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.ViewModel.WordsViewModel;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private WordsViewModel wordsViewModel;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.rclWords);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final WordsAdapter wordsAdapter = new WordsAdapter();
        recyclerView.setAdapter(wordsAdapter);

        wordsViewModel  = ViewModelProviders.of(this).get(WordsViewModel.class);
        wordsViewModel.getAllWword().observe(this, new Observer<List<Words>>() {
            @Override
            public void onChanged(List<Words> words) {
                //update recycle view later
                wordsAdapter.setWords(words);
                Toast.makeText(Main2Activity.this,"onChanged",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
