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

        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);
        progress = findViewById(R.id.progress);

        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.init(this);

        viewModel.getSourceHead().observe(this,sourceHead -> {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),sourceHead.getSources().size(),sourceHead);
            viewPager.setAdapter(adapter);
            tab.setupWithViewPager(viewPager);
        });

        viewModel.getIsLoading().observe(this,isLoading->{
            if (isLoading){
                progress.setVisibility(View.VISIBLE);
            }else{
                progress.setVisibility(View.GONE);
            }
        });


    }
}