package com.shubamvirdi.newsapp.Adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.shubamvirdi.newsapp.Fragments.DynamicFragment;
import com.shubamvirdi.newsapp.ModelClasses.SourceHead;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int size;
    private SourceHead head;
    private static final String TAG = ViewPagerAdapter.class.getSimpleName();

    public ViewPagerAdapter(@NonNull FragmentManager fm, int size, SourceHead head) {
        super(fm);
        this.size = size;
        this.head = head;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return DynamicFragment.newInstance(head.getSources().get(position).getId());
    }

    @Override
    public int getCount() {
        return size;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return head.getSources().get(position).getName();
    }
}
