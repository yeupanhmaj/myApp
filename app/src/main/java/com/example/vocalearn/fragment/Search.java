package com.example.vocalearn.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocalearn.AddWordActivity;
import com.example.vocalearn.CustomAdapter.WordsAdapter;
import com.example.vocalearn.Databases.WordsDAO;
import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.MyApplication;
import com.example.vocalearn.R;
import com.example.vocalearn.TuActivity;


import java.util.ArrayList;
import java.util.List;

public class Search extends Fragment implements WordsAdapter.OnWordClickListener {
    private Button btnAddmore;
    private RecyclerView recyclerView;
    private WordsDAO dbAccess;
    @Nullable
    private EditText txtSearch;
    private List<Words> mylist;
    private WordsAdapter wordsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        addControl(root);
        addEvent(root);
        getData();
        setRecyclerView(container, mylist);
        return root;
    }

    private void addEvent(View root) {
        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    //do what you want on the press of 'done'
                    getItemByKey(txtSearch.getText().toString());
                    wordsAdapter.setWords(mylist);
                    recyclerView.setAdapter(wordsAdapter);
                }
                return false;
            }
        });
        btnAddmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWordActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

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
    }//onActivityResult

    private void getItemByKey(String key) {
        List<Words> temp;
        dbAccess = dbAccess.getInstance(getActivity().getApplicationContext());
        dbAccess.openDB();
        temp = dbAccess.getAllWordsFromSub(key);
        mylist = temp;
        dbAccess.closeDB();
    }

    private void addControl(View root) {
        txtSearch = root.findViewById(R.id.txtSearch);
        recyclerView = root.findViewById(R.id.rclWords);
        btnAddmore = root.findViewById(R.id.btnAddmore);
    }


    private void getData() {
        dbAccess = dbAccess.getInstance(getActivity().getApplicationContext());
        dbAccess.openDB();
        mylist = dbAccess.getAllWords();
        dbAccess.closeDB();
    }

    private void setRecyclerView(ViewGroup container, List<Words> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setHasFixedSize(true);
        wordsAdapter = new WordsAdapter(this);
        wordsAdapter.setWords(list);
        recyclerView.setAdapter(wordsAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), TuActivity.class);
        intent.putExtra("Word", mylist.get(position));
        startActivityForResult(intent, 2);
    }
}
