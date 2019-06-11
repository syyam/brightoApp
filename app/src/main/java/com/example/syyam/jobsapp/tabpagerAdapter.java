package com.example.syyam.jobsapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.syyam.jobsapp.Fragments.ColorFinderFragment;
import com.example.syyam.jobsapp.Fragments.ProductfinderFragment;
import com.example.syyam.jobsapp.Fragments.ColorselectorFramgment;
import com.example.syyam.jobsapp.Fragments.LuxuryFramgment;
import com.example.syyam.jobsapp.Fragments.ProductFragment;
import com.example.syyam.jobsapp.Fragments.StoreFragment;

/**
 * Created by NIPU on 12/27/2017.
 */

public class tabpagerAdapter extends FragmentStatePagerAdapter {

    String[] tabarray = new String[]{"LATEST","PAPERS","CATEGORIES","COMPANIES","SECTORS","CITIES"};
    Integer tabnumber = 6;



    public tabpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return tabarray[position];
        return null;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:

                ColorFinderFragment three3 = new ColorFinderFragment();
                return three3;

            case 1:
                ProductfinderFragment six6 = new ProductfinderFragment();
                return six6;
            case 2:
                ColorselectorFramgment four4 = new ColorselectorFramgment();
                return four4;
            case 3:
                LuxuryFramgment one1 = new LuxuryFramgment();
                return one1;

            case 4:
                ProductFragment two2 = new ProductFragment();
                return two2;

            case 5:
                StoreFragment five5 = new StoreFragment();
                return five5;
        }


        return null;
    }

    @Override
    public int getCount() {
        return tabnumber;
    }
}
