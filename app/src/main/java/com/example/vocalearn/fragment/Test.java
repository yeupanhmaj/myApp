package com.example.vocalearn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocalearn.CustomAdapter.ChudeAdapter;
import com.example.vocalearn.Databases.ChuDeDAO;
import com.example.vocalearn.Entity.ChuDe;
import com.example.vocalearn.MyApplication;
import com.example.vocalearn.QuizActivity;
import com.example.vocalearn.R;
import com.example.vocalearn.StartingScreenActivity;

import java.util.List;

public class Test extends Fragment {
    private static final int REQUEST_CODE_QUIZ = 1;
    private Spinner spinnerChude;
    private Button buttonStartQuiz;
    private ChuDeDAO dbAccess;
    @Nullable
    private List<String> mylist;
    private String chude;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_starting_screen, container, false);
        addControl(root);
        getData();
        setSpiner();
        addEvent(root);
        return root;
    }

    private void addEvent(View view) {
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz(chude);
            }
        });
        spinnerChude.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                chude = parent.getItemAtPosition(pos).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void addControl(View view) {
        buttonStartQuiz = view.findViewById(R.id.button_start_quiz);
        spinnerChude = view.findViewById(R.id.spinnerChude);
    }

    private void startQuiz(String chude) {
        Intent intent = new Intent(getActivity(), QuizActivity.class);
        intent.putExtra("ChuDe",chude);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);

    }
    private void setSpiner()
    {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MyApplication.getContext(),
                android.R.layout.simple_spinner_item, mylist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChude.setAdapter(dataAdapter);
    }
    private void getData() {
        dbAccess = dbAccess.getInstance(getActivity().getApplicationContext());
        dbAccess.openDB();
        mylist = dbAccess.getAllNameChuDe();
        dbAccess.closeDB();
    }
}
