package com.shubamvirdi.newsapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;
import com.shubamvirdi.newsapp.Adapters.ViewPagerAdapter;
import com.shubamvirdi.newsapp.ModelClasses.Source;
import com.shubamvirdi.newsapp.ModelClasses.SourceHead;
import com.shubamvirdi.newsapp.R;
import com.shubamvirdi.newsapp.ViewModels.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String STATE = "state";
    private int STATE_VAL = -1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private TabLayout tab;
    private ViewPager viewPager;
    private ProgressBar progress;
    private ViewPagerAdapter adapter;
    private SourceHead mSourceHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // id initialization
        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);
        progress = findViewById(R.id.progress);

        mSourceHead = new SourceHead();
        mSourceHead.setSources(new ArrayList<>());


        // setting tab with view pager
        tab.setupWithViewPager(viewPager);

        // creating the view model object
        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // initiating the view model
        viewModel.init(this);

        // getting live data of sources
        viewModel.getSourceHead().observe(this, sourceHead -> {
            // setting view pager according to sources
            adapter = new ViewPagerAdapter(getSupportFragmentManager(), sourceHead.getSources().size(), sourceHead);
            viewPager.setAdapter(adapter);

            // if the state of the current page i saved
            if (STATE_VAL!= -1){

                // load that page to that position
                viewPager.setCurrentItem(STATE_VAL);
            }
            adapter.notifyDataSetChanged();
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // saving the current page
        outState.putInt(STATE,viewPager.getCurrentItem());
        Log.e(TAG, "onSaveInstanceState: "+ viewPager.getCurrentItem());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // getting the saved current page
        STATE_VAL = savedInstanceState.getInt(STATE);
        Log.e(TAG, "onRestoreInstanceState: "+ savedInstanceState.getInt(STATE));
    }

}