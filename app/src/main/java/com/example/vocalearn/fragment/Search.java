package com.example.vocalearn.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocalearn.CustomAdapter.WordsAdapter;
import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.Main2Activity;
import com.example.vocalearn.R;
import com.example.vocalearn.ViewModel.WordsViewModel;

import java.util.List;

public class Search extends Fragment {
    private WordsViewModel wordsViewModel;
    private RecyclerView recyclerView;
    @Nullable
    EditText txtSearch;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        txtSearch = root.findViewById(R.id.txtSearch);
        recyclerView = root.findViewById(R.id.rclWords);

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        final WordsAdapter wordsAdapter = new WordsAdapter();
        recyclerView.setAdapter(wordsAdapter);

        wordsViewModel  = ViewModelProviders.of(getActivity()).get(WordsViewModel.class);
        wordsViewModel.getAllWword().observe(getActivity(), new Observer<List<Words>>() {
            @Override
            public void onChanged(List<Words> words) {
                //update recycle view later
                wordsAdapter.setWords(words);

            }
        });
        return root;
    }
}
