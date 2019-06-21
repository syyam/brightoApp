package com.example.syyam.jobsapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SpecificShade extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    int r, g, b;
    String name, desc, itemCode;
    LinearLayout linearLayout;
    TextView nameTV, descTV, itemcodeTV;

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_specific_shade);


//        getSupportActionBar().setTitle("");
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.White)));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.White)));
        actionBar.setTitle("");
        actionBar.show();


        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl.addDrawerListener(t);
        t.syncState();


        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_Login) {
                    Intent L = new Intent(SpecificShade.this, LoginActivity.class);
                    startActivity(L);
                } else {
                    dl.closeDrawer(GravityCompat.START);
                    return true;
                }


                return true;

            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationMenuView navMenuView = (NavigationMenuView) nv.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(SpecificShade.this, DividerItemDecoration.VERTICAL));

        if (getIntent().hasExtra("R") &&
                getIntent().hasExtra("G") &&
                getIntent().hasExtra("B") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("desc") &&
                getIntent().hasExtra("itemCode")) {

            String R = getIntent().getStringExtra("R");
            String G = getIntent().getStringExtra("G");
            String B = getIntent().getStringExtra("B");

            String getName = getIntent().getStringExtra("name");
            String getDesc = getIntent().getStringExtra("desc");
            String getItemCode = getIntent().getStringExtra("itemCode");


            if (
                    !TextUtils.isEmpty(R) &&
                            !TextUtils.isEmpty(G) &&
                            !TextUtils.isEmpty(B) &&
                            !TextUtils.isEmpty(getName) &&
                            !TextUtils.isEmpty(getDesc) &&
                            !TextUtils.isEmpty(getItemCode)
                    ) {

                r = Integer.parseInt(getIntent().getStringExtra("R"));
                g = Integer.parseInt(getIntent().getStringExtra("G"));
                b = Integer.parseInt(getIntent().getStringExtra("B"));

                name = getIntent().getStringExtra("name");
                desc = getIntent().getStringExtra("desc");
                itemCode = getIntent().getStringExtra("itemCode");
            }


        }// if end


        linearLayout = (LinearLayout) findViewById(R.id.backColor);
        nameTV = (TextView) findViewById(R.id.name);
        descTV = (TextView) findViewById(R.id.description);
        itemcodeTV = (TextView) findViewById(R.id.itemCode);

        linearLayout.setBackgroundColor(Color.rgb(r, g, b));
        nameTV.setText(name);
        descTV.setText(desc);
        itemcodeTV.setText(itemCode);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fav_share_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (t.onOptionsItemSelected(item)) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Intent L = new Intent(this, SearchActivity.class);
            startActivity(L);
        }

        if (id == R.id.action_back) {
            finish();
        }
        if (id == R.id.action_share) {

        }
        if (id == R.id.action_fav) {

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_Login) {
            Intent L = new Intent(this, LoginActivity.class);
            startActivity(L);
        } else {
            dl.closeDrawer(GravityCompat.START);
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
