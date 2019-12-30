package com.example.vocalearn.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocalearn.CustomAdapter.ChudeAdapter;
import com.example.vocalearn.Databases.ChuDeDAO;
import com.example.vocalearn.Databases.WordsDAO;
import com.example.vocalearn.Entity.ChuDe;
import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.MyApplication;
import com.example.vocalearn.QuizActivity;
import com.example.vocalearn.R;
import com.example.vocalearn.SharedReference.MyRF;
import com.example.vocalearn.StartingScreenActivity;

import java.util.List;

public class Test extends Fragment {
    private static final int REQUEST_CODE_QUIZ = 1;
    private static final String PRACTICE_MODE = "mode";
    private Spinner spinnerChude;
    private Button buttonStartQuiz,btnMeanToWord,btnWordToMean;
    private ChuDeDAO dbAccess;
    private WordsDAO dbWords;
    private RadioButton rdbFav;
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
                startQuiz(chude,0);
            }
        });
        btnWordToMean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz(chude,1);
            }
        });
        btnMeanToWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz(chude,2);
            }
        });
        spinnerChude.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                chude = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        rdbFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rdbFav.isSelected()) {
                    rdbFav.setChecked(true);
                    rdbFav.setSelected(true);
                } else {
                    rdbFav.setChecked(false);
                    rdbFav.setSelected(false);
                }
            }
        });
    }

    private void addControl(View view) {
        buttonStartQuiz = view.findViewById(R.id.button_start_quiz);
        spinnerChude = view.findViewById(R.id.spinnerChude);
        rdbFav = view.findViewById(R.id.rdbFav);
        btnMeanToWord=view.findViewById(R.id.btnMeanToWord);
        btnWordToMean=view.findViewById(R.id.btnWordToMean);
    }

    private void startQuiz(String chude,int mode) {
        final Intent intent = new Intent(getActivity(), QuizActivity.class);
        intent.putExtra("ChuDe", chude);
        intent.putExtra(PRACTICE_MODE,mode);
        if (rdbFav.isChecked()) {
            if (count(chude, 1) > 30)
                intent.putExtra("fav", 1);
            else
            {
                final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage("Dont't Have Enough Favorite Word, Start Practice Normal");
                alert.setTitle("Warning");

                //chốt tính năng
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        startActivityForResult(intent, REQUEST_CODE_QUIZ);
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }

        } else {
            intent.putExtra("fav", 0);
            startActivityForResult(intent, REQUEST_CODE_QUIZ);
        }

    }

    private void setSpiner() {
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

    private int count(String ChuDe, int fav) {
        dbWords = dbWords.getInstance(getActivity().getApplicationContext());
        dbWords.openDB();
        dbWords.closeDB();
        return dbWords.countFav(ChuDe, fav);
    }
}
