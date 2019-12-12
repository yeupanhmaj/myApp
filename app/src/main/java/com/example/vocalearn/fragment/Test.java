package com.example.vocalearn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vocalearn.QuizActivity;
import com.example.vocalearn.R;
import com.example.vocalearn.StartingScreenActivity;

public class Test extends Fragment {
    private static final int REQUEST_CODE_QUIZ = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_starting_screen, container, false);
        Button buttonStartQuiz = root.findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
        return root;
    }
    private void startQuiz() {
        Intent intent = new Intent(getActivity(), QuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }
}
