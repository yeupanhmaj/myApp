package com.example.vocalearn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.vocalearn.Databases.WordsDAO;
import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.SqliteOpenHelper.DatabaseOpenHelper;

import java.security.Key;
import java.util.Locale;

public class TuActivity extends AppCompatActivity {
    private final String KEY_WORDS = "Word";

    private TextView txtWord;
    private TextView txtNghia;
    private TextView txtGhiChu;
    private ImageButton imageButton,imgbtnFav;
    private TextToSpeech mTTS;
    private TextView txtPhatAm;
    private Button btnFav,btnRemove;
    private Words words;
    private Intent intent;


    private WordsDAO dbAccess;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_activity);
        intent = getIntent();
        words = intent.getParcelableExtra(KEY_WORDS);
        addControl();
        addEvent();
        words = getWords(words.getTu());
        if(words.getFavorite() == 1)
        {
            imgbtnFav.setBackground(getDrawable(R.drawable.ic_star_active));
        }
        else if(words.getFavorite() == 0)
        {
            imgbtnFav.setBackground(getDrawable(R.drawable.ic_star_empty));
        }
        txtWord.setText(words.getTu());
        txtNghia.setText(words.getNghia());
        txtGhiChu.setText(words.getGhichu());
        txtPhatAm.setText(words.getPhatAm());
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
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fav = words.getFavorite();
                if(fav == 1)
                {
                    updateFav(words,0);
                    words = getWords(words.getTu());
                    imgbtnFav.setBackground(getDrawable(R.drawable.ic_star_empty));
                }
                else if(fav == 0)
                {
                    updateFav(words,1);
                    words = getWords(words.getTu());
                    imgbtnFav.setBackground(getDrawable(R.drawable.ic_star_active));
                }
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(TuActivity.this);
                final EditText edittext = new EditText(TuActivity.this);
                alert.setTitle("Warning");
                alert.setMessage("Delete this word from library ?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                        deteleWord(words.getTu());
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result",2);
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.

                    }
                });
                alert.show();
            }
        });
    }

    private void addControl() {
        txtWord = findViewById(R.id.Tu);
        txtNghia= findViewById(R.id.Nghia);
        txtGhiChu= findViewById(R.id.GhiChu);
        txtPhatAm= findViewById(R.id.PhatAm);
        imageButton = findViewById(R.id.imageButton);
        imgbtnFav = findViewById(R.id.imgbtnFav);
        btnFav = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
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
    private Words getWords(String Key)
    {
        Words temp;
        dbAccess = dbAccess.getInstance(MyApplication.getContext());
        dbAccess.openDB();
        temp = dbAccess.getWord(Key);
        dbAccess.closeDB();
        return temp;
    }
    private void updateFav(Words w,int Key)
    {
        dbAccess = dbAccess.getInstance(MyApplication.getContext());
        dbAccess.openDB();
        dbAccess.updateWord(w,Key);
        dbAccess.closeDB();
    }
    private void deteleWord(String Key)
    {
        dbAccess = dbAccess.getInstance(MyApplication.getContext());
        dbAccess.openDB();
        dbAccess.deleteAt(Key);
        dbAccess.closeDB();
    }
}