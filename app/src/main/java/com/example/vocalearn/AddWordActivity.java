package com.example.vocalearn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.vocalearn.Databases.WordsDAO;
import com.example.vocalearn.Entity.Words;

import java.util.ArrayList;

public class AddWordActivity extends AppCompatActivity {
    private final String[] Loaitu = {"Đại từ",
            "Tính từ",
            "Trạng từ",
            "Động từ",
            "Động từ bất quy tắc",
            "Giới từ"
    };
    private final Boolean[] selectLoaiTu = {false,
            false,
            false,
            false,
            false,
            false
    };

    private RadioGroup rbGroup;
    private EditText txtEditTu, txtEditNghia, txtEditNote;
    private Button btnSave, btnCancel;
    private WordsDAO dbAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addword_activity);
        addControl();
        setRadio();
        addEvent();
    }

    private void setRadio() {

        for (int i = 0; i < selectLoaiTu.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(Loaitu[i]);
            radioButton.setChecked(selectLoaiTu[i]);
            rbGroup.addView(radioButton);
        }
    }

    private void addEvent() {

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtEditTu.getText().toString().equals("")) {
                    Words w = new Words();
                    String s;
                    w.setTu(txtEditTu.getText().toString());
                    w.setNghia(txtEditNghia.getText().toString());
                    s = (txtEditNote.getText().toString());
                    for (int i = 0; i < rbGroup.getChildCount(); i++) {
                        View view = rbGroup.getChildAt(i);
                        if (view instanceof RadioButton) {
                            if (((RadioButton) view).isChecked()) {
                                s = ((RadioButton) view).getText() + "\n" + s;
                            }
                        }
                    }
                    w.setGhichu(s);
                    insertWord(w);
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",1);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                } else {
                    Toast.makeText(AddWordActivity.this, "Enter word in english", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
    }

    private void addControl() {
        txtEditTu = findViewById(R.id.txtEditTu);
        txtEditNghia = findViewById(R.id.txtEditNghia);
        txtEditNote = findViewById(R.id.txtEditNote);
        rbGroup = findViewById(R.id.rbGroup);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
    }

    private void insertWord(Words w) {
        dbAccess = dbAccess.getInstance(MyApplication.getContext());
        dbAccess.openDB();
        dbAccess.insertWord(w);
        dbAccess.closeDB();
    }
}
