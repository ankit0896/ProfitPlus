package com.example.profitplus.view.adpater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.profitplus.view.fragments.FundFragment;
import com.example.profitplus.view.fragments.OrderFragment;
import com.example.profitplus.view.fragments.PortfolioFragment;
import com.example.profitplus.view.fragments.ResearchFragment;
import com.example.profitplus.view.fragments.WatchListFragment;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public HomePagerAdapter(@NonNull FragmentManager fm, int mNoOfTabs) {
        super(fm);
        this.mNoOfTabs = mNoOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                WatchListFragment watchListFragment = new WatchListFragment();
                return watchListFragment;
            case 1:
                PortfolioFragment portfolioFragment = new PortfolioFragment();
                return  portfolioFragment;
            case 2:
                OrderFragment orderFragment = new OrderFragment();
                return  orderFragment;
            case 3:
                FundFragment fundFragment = new FundFragment();
                return  fundFragment;
            case 4:
                ResearchFragment researchFragment = new ResearchFragment();
                return  researchFragment;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
