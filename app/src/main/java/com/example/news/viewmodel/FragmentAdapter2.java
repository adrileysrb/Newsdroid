package com.example.news.viewmodel;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.news.view.CelebrityFragment;
import com.example.news.view.PoliticFragment;
import com.example.news.view.ScienceFragment;
import com.example.news.view.SearchFragment;
import com.example.news.view.SportsFragment;
import com.example.news.view.TechnologyFragment;

public class FragmentAdapter2 extends FragmentStateAdapter {

    public FragmentAdapter2(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void setData(String newText) {
        query = newText;
    }
    String query;
    @Override
    public Fragment createFragment(int position) {

        return new SearchFragment(query);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
