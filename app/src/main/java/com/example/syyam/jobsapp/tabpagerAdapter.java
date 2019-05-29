package com.example.syyam.jobsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.syyam.jobsapp.Fragments.CategoriesFragment;
import com.example.syyam.jobsapp.Fragments.CitiesFragment;
import com.example.syyam.jobsapp.Fragments.CompaniesFragment;
import com.example.syyam.jobsapp.Fragments.LatestFragment;
import com.example.syyam.jobsapp.Fragments.PapersFragment;
import com.example.syyam.jobsapp.Fragments.SectorsFragment;

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
        return tabarray[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                LatestFragment one1 = new LatestFragment();
                return one1;
            case 1:
                PapersFragment two2 = new PapersFragment();
                return two2;
            case 2:
                CategoriesFragment three3 = new CategoriesFragment();
                return three3;
            case 3:
                CompaniesFragment four4 = new CompaniesFragment();
                return four4;
            case 4:
                SectorsFragment five5 = new SectorsFragment();
                return five5;
            case 5:
                CitiesFragment six6 = new CitiesFragment();
                return six6;
        }


        return null;
    }

    @Override
    public int getCount() {
        return tabnumber;
    }
}
