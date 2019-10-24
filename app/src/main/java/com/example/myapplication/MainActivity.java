package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.adapter.SectionsPagerAdapter;
import com.example.myapplication.ui.FragmentFavorite;
import com.example.myapplication.ui.FragmentHome;
import com.example.myapplication.ui.FragmentUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter sectionPagerAdapter;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         bottomNavigationView = findViewById(R.id.bot_menu);
         bottomNavigationView.setOnNavigationItemSelectedListener(nav);

        viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId())
            {
                case R.id.nav_home:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.nav_user:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.nav_favorite:
                    viewPager.setCurrentItem(1);
                    break;
            }
            return true;
        }
    };
    private void setupViewPager(ViewPager viewPager)
    {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentHome(),"Home");
        adapter.addFragment(new FragmentFavorite(),"Favorite");
        adapter.addFragment(new FragmentUser(),"User");
        viewPager.setAdapter(adapter);
    }
}
