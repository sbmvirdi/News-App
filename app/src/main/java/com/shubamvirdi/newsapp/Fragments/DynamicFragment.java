package com.shubamvirdi.newsapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shubamvirdi.newsapp.ModelClasses.Article;
import com.shubamvirdi.newsapp.R;
import com.shubamvirdi.newsapp.ViewModels.DynamicFragmentViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DynamicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DynamicFragment extends Fragment {

    private static final String SOURCE ="source";
    private RecyclerView newsRec;
    private String newsChannelId;


    public DynamicFragment() {
        // Required empty public constructor
    }

    public static DynamicFragment newInstance(String source) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putString(SOURCE, source);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dynamic, container, false);
        newsRec = view.findViewById(R.id.newsRec);
        if (getArguments().getString(SOURCE)!=null){
            newsChannelId = getArguments().getString(SOURCE);
        }

        DynamicFragmentViewModel viewModel = new ViewModelProvider(this).get(DynamicFragmentViewModel.class);
        viewModel.init(getContext(),newsChannelId);
        viewModel.getNewsData().observe(getViewLifecycleOwner(),newsData -> {
            for (Article article :newsData.getArticles()) {
                Log.e("newsData", article.getTitle() + "");
            }
        });
        return view;
    }
}