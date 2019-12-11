package com.example.vocalearn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocalearn.CustomAdapter.WordsAdapter;
import com.example.vocalearn.Databases.WordsDAO;
import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.R;
import com.example.vocalearn.TuActivity;


import java.util.List;

public class Search extends Fragment implements WordsAdapter.OnWordClickListener {

    private RecyclerView recyclerView;
    private WordsDAO dbAccess;
    @Nullable
    private EditText txtSearch;
    private List<Words> mylist;

    private WordsAdapter wordsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        txtSearch = root.findViewById(R.id.txtSearch);
        recyclerView = root.findViewById(R.id.rclWords);

        getData();

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setHasFixedSize(true);
        wordsAdapter = new WordsAdapter(this);
        wordsAdapter.setWords(mylist);
        recyclerView.setAdapter(wordsAdapter);
        return root;
    }


    private void getData()
    {
        dbAccess = dbAccess.getInstance(getActivity().getApplicationContext());
        dbAccess.openDB();
        mylist = dbAccess.getAllWords();
        dbAccess.closeDB();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), TuActivity.class);
        intent.putExtra("Word",mylist.get(position));
        startActivity(intent);
    }
}
