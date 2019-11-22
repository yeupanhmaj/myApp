package com.example.vocalearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.fragment.Home;
import com.example.vocalearn.fragment.Search;
import com.example.vocalearn.fragment.Test;
import com.example.vocalearn.fragment.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swapFragment(new Home());
        addControll();
        addEvent();
    }

    private void addControll()
    {
        bottomNavigationView= findViewById(R.id.bot_nav);
    }
    private void addEvent()
    {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFm = null;
                switch (menuItem.getItemId())
                {
                    case R.id.nav_home:
                        selectedFm = new Home();
                        swapFragment(selectedFm);
                        break;
                    case R.id.nav_test:
                        Intent intent = new Intent(MainActivity.this,StartingScreenActivity.class);
                        startActivity(intent);
//                        selectedFm = new Test();
//                        swapFragment(selectedFm);
                        break;
                    case R.id.nav_search:
                        selectedFm = new Search();
                        swapFragment(selectedFm);
                        break;
                    case R.id.nav_user:
                        selectedFm = new User();
                        swapFragment(selectedFm);
                        break;
                }
                return true;
            }
        });
    }
    private void swapFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main, fragment);
        fragmentTransaction.commit();
    }

}
