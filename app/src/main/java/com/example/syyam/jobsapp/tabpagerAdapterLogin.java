package com.example.syyam.jobsapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.syyam.jobsapp.Fragments.ColorFinderFragment;
import com.example.syyam.jobsapp.Fragments.LoginFragment;
import com.example.syyam.jobsapp.Fragments.ProductfinderFragment;
import com.example.syyam.jobsapp.Fragments.ColorselectorFramgment;
import com.example.syyam.jobsapp.Fragments.LuxuryFramgment;
import com.example.syyam.jobsapp.Fragments.ProductFragment;
import com.example.syyam.jobsapp.Fragments.RegisterFragment;
import com.example.syyam.jobsapp.Fragments.StoreFragment;

/**
 * Created by NIPU on 12/27/2017.
 */

class tabpagerAdapterLogin extends FragmentStatePagerAdapter {

    String[] tabarray = new String[]{"LOGIN", "REGISTER"};
    Integer tabnumber = 2;


    public tabpagerAdapterLogin(FragmentManager fm) {
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

                LoginFragment three3 = new LoginFragment();
                return three3;

            case 1:
                RegisterFragment six6 = new RegisterFragment();
                return six6;

        }


        return null;
    }

    @Override
    public int getCount() {
        return tabnumber;
    }
}
