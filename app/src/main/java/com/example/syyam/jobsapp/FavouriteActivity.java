package com.example.syyam.jobsapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class FavouriteActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager Pager;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);


        toolbar = (Toolbar) findViewById(R.id.toolbar_fav);
        setSupportActionBar(toolbar);


        tabLayout = (TabLayout) findViewById(R.id.tabs_fav);
        Pager = (ViewPager) findViewById(R.id.viewpager_fav);

        tabPagerAdapterFav Tabpageradapter = new tabPagerAdapterFav(getSupportFragmentManager());
        Pager.setAdapter(Tabpageradapter);
        tabLayout.setupWithViewPager(Pager);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_fav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_fav);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(FavouriteActivity.this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();


        if (id == R.id.nav_Login) {
            Intent L = new Intent(this, LoginActivity.class);
            startActivity(L);
        }
        if (id == R.id.nav_designerPalettes) {
            Intent L = new Intent(this, DesignerPalettedActivity.class);
            startActivity(L);
        }
        if (id == R.id.nav_fav) {
            Intent L = new Intent(this, FavouriteActivity.class);
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
