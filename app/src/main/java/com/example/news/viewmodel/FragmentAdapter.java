package com.example.news.viewmodel;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.news.view.CelebrityFragment;
import com.example.news.view.PoliticFragment;
import com.example.news.view.ScienceFragment;
import com.example.news.view.SportsFragment;
import com.example.news.view.TechnologyFragment;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter( FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
               return new ScienceFragment();
            case 2:
                return new PoliticFragment();
            case 3:
                return new TechnologyFragment();
            case 4:
                return new CelebrityFragment();
        }
        return new SportsFragment();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
