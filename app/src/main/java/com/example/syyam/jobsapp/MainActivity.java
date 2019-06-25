package com.example.syyam.jobsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import net.skoumal.fragmentback.BackFragmentHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int currentPage = 0;
    private class PageListener extends ViewPager.SimpleOnPageChangeListener { // to change tab icons on selection
        public void onPageSelected(int position) {
            int noOfPages = 6;
             currentPage = position;
            invalidateOptionsMenu();
            if (currentPage == 0) {
                tabLayout.getTabAt(0).setIcon(tabIconsSelected[0]);
                tabLayout.getTabAt(1).setIcon(tabIcons[1]);
                tabLayout.getTabAt(2).setIcon(tabIcons[2]);
                tabLayout.getTabAt(3).setIcon(tabIcons[3]);
                tabLayout.getTabAt(4).setIcon(tabIcons[4]);
                tabLayout.getTabAt(5).setIcon(tabIcons[5]);
                toolbar.setTitle("Color Finder");

            } else if (currentPage == 1) {
                tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                tabLayout.getTabAt(1).setIcon(tabIconsSelected[1]);
                tabLayout.getTabAt(2).setIcon(tabIcons[2]);
                tabLayout.getTabAt(3).setIcon(tabIcons[3]);
                tabLayout.getTabAt(4).setIcon(tabIcons[4]);
                tabLayout.getTabAt(5).setIcon(tabIcons[5]);
                toolbar.setTitle("Product Finder");

            } else if (currentPage == 2) {
                tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                tabLayout.getTabAt(1).setIcon(tabIcons[1]);
                tabLayout.getTabAt(2).setIcon(tabIconsSelected[2]);
                tabLayout.getTabAt(3).setIcon(tabIcons[3]);
                tabLayout.getTabAt(4).setIcon(tabIcons[4]);
                tabLayout.getTabAt(5).setIcon(tabIcons[5]);
                toolbar.setTitle("Color Selector");
            } else if (currentPage == 3) {
                tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                tabLayout.getTabAt(1).setIcon(tabIcons[1]);
                tabLayout.getTabAt(2).setIcon(tabIcons[2]);
                tabLayout.getTabAt(3).setIcon(tabIconsSelected[3]);
                tabLayout.getTabAt(4).setIcon(tabIcons[4]);
                tabLayout.getTabAt(5).setIcon(tabIcons[5]);
                toolbar.setTitle("Luxury Finish");
            } else if (currentPage == 4) {
                tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                tabLayout.getTabAt(1).setIcon(tabIcons[1]);
                tabLayout.getTabAt(2).setIcon(tabIcons[2]);
                tabLayout.getTabAt(3).setIcon(tabIcons[3]);
                tabLayout.getTabAt(4).setIcon(tabIconsSelected[4]);
                tabLayout.getTabAt(5).setIcon(tabIcons[5]);
                toolbar.setTitle("Product");
            } else if (currentPage == 5) {
                tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                tabLayout.getTabAt(1).setIcon(tabIcons[1]);
                tabLayout.getTabAt(2).setIcon(tabIcons[2]);
                tabLayout.getTabAt(3).setIcon(tabIcons[3]);
                tabLayout.getTabAt(4).setIcon(tabIcons[4]);
                tabLayout.getTabAt(5).setIcon(tabIconsSelected[5]);
                toolbar.setTitle("Store Locator");
            }
        }
    }

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager Pager;
    private DrawerLayout drawer;
    private int[] tabIcons = {
            R.mipmap.menu_colorfinder,
            R.mipmap.menu_productfinder,
            R.mipmap.menu_colorselector,
            R.mipmap.menu_luxury,
            R.mipmap.menu_product,
            R.mipmap.menu_store,
    };

    private int[] tabIconsSelected = {
            R.mipmap.menu_colorfinder_select,
            R.mipmap.menu_productfinder_select,
            R.mipmap.menu_colorselector_select,
            R.mipmap.menu_luxury_select,
            R.mipmap.menu_product_select,
            R.mipmap.menu_store_select,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Color Finder");
        setSupportActionBar(toolbar);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        Pager = (ViewPager) findViewById(R.id.viewpager);

        tabpagerAdapter Tabpageradapter = new tabpagerAdapter(getSupportFragmentManager());
        Pager.setAdapter(Tabpageradapter);
        tabLayout.setupWithViewPager(Pager);
        setupTabIcons();

        PageListener pagelistener = new PageListener();
        Pager.setOnPageChangeListener(pagelistener);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));

    }

    private void setupTabIcons() {


        tabLayout.getTabAt(0).setIcon(tabIconsSelected[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(5).setIcon(tabIcons[5]);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!BackFragmentHelper.fireOnBackPressedEvent(this)) {
            // lets do the default back action if fragments don't consume it
            //super.onBackPressed();
        } else {
            //super.onBackPressed();
        }

        // first ask your fragments to handle back-pressed event

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        if(currentPage==0 || currentPage==4){
            menu.getItem(0).setVisible(true);
        }
        else{
            menu.getItem(0).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.network_security_config.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Intent L = new Intent(this, SearchActivity.class);
            startActivity(L);
        }
        else if(id==R.id.filter){
            startActivity(new Intent(MainActivity.this,FiltersActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_Login) {
            Intent L = new Intent(this, LoginActivity.class);
            startActivity(L);
        }
        if (id == R.id.nav_designerPalettes) {
            Intent L = new Intent(this, DesignerPalettedActivity.class);
            startActivity(L);
        } else {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

//        if (id == R.id.nav_home) {
//            // Handle the camera action
//        } else if (id == R.id.nav_search) {
//            Intent L = new Intent(this, SearchActivity.class);
//            startActivity(L);
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_rate) {
//
//        } else if (id == R.id.nav_exit) {
//
//        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        return true;
    }
}
