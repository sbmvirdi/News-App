package com.shubamvirdi.newsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;
import com.shubamvirdi.newsapp.Adapters.ViewPagerAdapter;
import com.shubamvirdi.newsapp.R;
import com.shubamvirdi.newsapp.ViewModels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager viewPager;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // id initialization
        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);
        progress = findViewById(R.id.progress);

        // creating the view model object
        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // initiating the view model
        viewModel.init(this);

        // getting live data of sources
        viewModel.getSourceHead().observe(this, sourceHead -> {
            // setting view pager according to sources
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), sourceHead.getSources().size(), sourceHead);
            viewPager.setAdapter(adapter);

            // setting tab with view pager
            tab.setupWithViewPager(viewPager);
        });

        // getting live data of is loading boolean value
        viewModel.getIsLoading().observe(this, isLoading -> {
            // if data is loading
            if (isLoading) {
                progress.setVisibility(View.VISIBLE);
            }
            // if data is loaded
            else {
                progress.setVisibility(View.GONE);
            }
        });


    }
}