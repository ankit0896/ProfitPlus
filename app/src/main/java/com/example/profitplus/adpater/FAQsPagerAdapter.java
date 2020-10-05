package com.example.profitplus.adpater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.profitplus.fragments.ApplicationFragment;
import com.example.profitplus.fragments.HelpFragment;
import com.example.profitplus.fragments.OrderFragment;
import com.example.profitplus.fragments.PortfolioFragment;
import com.example.profitplus.fragments.StockFragment;
import com.example.profitplus.fragments.WatchListFragment;

public class FAQsPagerAdapter  extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public FAQsPagerAdapter(@NonNull FragmentManager fm, int mNoOfTabs) {
        super(fm);
        this.mNoOfTabs = mNoOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                StockFragment stockFragment = new StockFragment();
                return stockFragment;
            case 1:
                ApplicationFragment applicationFragment = new ApplicationFragment();
                return  applicationFragment;
            case 2:
                HelpFragment helpFragment = new HelpFragment();
                return  helpFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
