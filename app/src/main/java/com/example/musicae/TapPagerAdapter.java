package com.example.musicae;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TapPagerAdapter extends FragmentStatePagerAdapter {

    String[] tabarray = new String[]{"Canciones","PlayLists"};

    public TapPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabarray[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new MusicFragment();
            case 1: return new PlayListFragment();
            default: return new MusicFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
