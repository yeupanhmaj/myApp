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

import com.example.vocalearn.CustomAdapter.ChudeAdapter;
import com.example.vocalearn.CustomAdapter.WordsAdapter;
import com.example.vocalearn.Databases.ChuDeDAO;
import com.example.vocalearn.Databases.WordsDAO;
import com.example.vocalearn.Entity.ChuDe;
import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.QuizActivity;
import com.example.vocalearn.R;
import com.example.vocalearn.TuActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Home extends Fragment implements ChudeAdapter.OnChuDeClickListener{
    private RecyclerView recyclerView;
    private static final int REQUEST_CODE_QUIZ = 1;
    private static final String PRACTICE_MODE = "mode";
    private String chude;
    private ChuDeDAO dbAccess;
    @Nullable
    private List<ChuDe> mylist;

    private ChudeAdapter chudeAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.rclChuDe);
        getData();

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setHasFixedSize(true);
        chudeAdapter = new ChudeAdapter( this);
        chudeAdapter.setChuDe(mylist);
        recyclerView.setAdapter(chudeAdapter);
        return root;
    }

    private void getData() {
        dbAccess = dbAccess.getInstance(getActivity().getApplicationContext());
        dbAccess.openDB();
        mylist = dbAccess.getAllChuDe();
        dbAccess.closeDB();
    }

    public void onItemClick(int position) {
        Random r = new Random();
        chude=mylist.get(position).getTenChuDe();
        Intent intent = new Intent(getActivity(), QuizActivity.class);
        intent.putExtra("ChuDe",chude);
        intent.putExtra(PRACTICE_MODE, r.nextInt(3)+1);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }
}
