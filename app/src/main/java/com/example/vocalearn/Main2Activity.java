package com.example.vocalearn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.vocalearn.CustomAdapter.WordsAdapter;
import com.example.vocalearn.Databases.WordsDAO;
import com.example.vocalearn.Entity.Words;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements WordsAdapter.OnWordClickListener{

    private RecyclerView recyclerView;
    private WordsDAO dbAccess;
    private List<Words> mylist;
    private WordsAdapter wordsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.rclWords);
        dbAccess = dbAccess.getInstance(getApplicationContext());
        dbAccess.openDB();

        mylist = dbAccess.getAllFavoriteWords();

        dbAccess.closeDB();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        wordsAdapter = new WordsAdapter(this);
        wordsAdapter.setWords(mylist);
        recyclerView.setAdapter(wordsAdapter);
        if(mylist.size() <= 0)
            Toast.makeText(Main2Activity.this,"You don't have any favorite word",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                wordsAdapter.setWords(mylist);
                recyclerView.setAdapter(wordsAdapter);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                getData();
                wordsAdapter.setWords(mylist);
                recyclerView.setAdapter(wordsAdapter);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
    private void getData() {
        dbAccess = dbAccess.getInstance(Main2Activity.this);
        dbAccess.openDB();
        mylist = dbAccess.getAllFavoriteWords();
        dbAccess.closeDB();
    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(Main2Activity.this, TuActivity.class);
        intent.putExtra("Word", mylist.get(position));
        startActivityForResult(intent, 2);
    }
}
