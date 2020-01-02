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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.SharedReference.MyRF;
import com.example.vocalearn.fragment.Home;
import com.example.vocalearn.fragment.Search;
import com.example.vocalearn.fragment.Test;
import com.example.vocalearn.fragment.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private SharedPreferences sp;
    private String TAG ="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCenter.start(getApplication(), "0bd5324f-031c-40bd-ae0a-1b91d24ffd25",
                Analytics.class, Crashes.class);
        swapFragment(new Home());
        addControll();
        addEvent();
    }

    private void addControll() {
        bottomNavigationView = findViewById(R.id.bot_nav);
    }

    private void addEvent() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFm = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        selectedFm = new Home();
                        swapFragment(selectedFm);
                        break;
                    case R.id.nav_test:
                        selectedFm = new Test();
                        swapFragment(selectedFm);
                        break;
                    case R.id.nav_search:
                        selectedFm = new Search();
                        swapFragment(selectedFm);
                        break;
                    case R.id.nav_user:
                        selectedFm = new User();
                        swapFragment(selectedFm);
//                        Intent intent = new Intent(MyApplication.getContext(),ScheduleActivity.class);
//                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main, fragment);
        fragmentTransaction.commit();
    }

}
