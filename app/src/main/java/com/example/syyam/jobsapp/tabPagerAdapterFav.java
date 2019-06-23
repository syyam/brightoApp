package com.example.syyam.jobsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.syyam.jobsapp.Fragments.PaletteFavFragment;
import com.example.syyam.jobsapp.Fragments.ProductFavFragment;
import com.example.syyam.jobsapp.Fragments.ShadeFavFragment;

public class tabPagerAdapterFav extends FragmentStatePagerAdapter {
    String[] tabarray = new String[]{"PRODUCTS", "SHADES", "PALLETS"};
    Integer tabnumber = 3;


    public tabPagerAdapterFav(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabarray[position];
        //return null;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ProductFavFragment three3 = new ProductFavFragment();
                return three3;

            case 1:
                ShadeFavFragment six6 = new ShadeFavFragment();
                return six6;

            case 2:
                PaletteFavFragment six7 = new PaletteFavFragment();
                return six7;

        }


        return null;
    }

    @Override
    public int getCount() {
        return tabnumber;
    }
}
