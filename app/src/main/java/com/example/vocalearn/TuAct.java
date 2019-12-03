package com.example.vocalearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vocalearn.Entity.Words;

import java.util.Locale;

public class TuAct extends AppCompatActivity {
    private TextView txtWord;
    private TextView txtNghia;
    private TextView txtGhiChu;
    private ImageButton imageButton;
    private TextToSpeech mTTS;
    private String Text2Speech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_activity);
        addControl();
        addEvent();
        Intent intent = getIntent();
        Words words = intent.getParcelableExtra("Word");
        txtWord.setText(words.getTu());
        txtNghia.setText(words.getNghia());
        txtGhiChu.setText(words.getGhichu());
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        imageButton.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
    }

    private void addEvent() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak((String)txtWord.getText());
            }
        });
    }

    private void addControl() {
        txtWord = findViewById(R.id.Tu);
        txtNghia= findViewById(R.id.Nghia);
        txtGhiChu= findViewById(R.id.GhiChu);
        imageButton = findViewById(R.id.imageButton);
    }
    private void speak(String text) {
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
    }
}