package com.highway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.highway.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import fragment.DashBoardFragment;
import fragment.NewBookingFragmentMapActivity;
import utils.Constants;
import utils.HighwayPreface;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    DrawerLayout drawer;
    private TextView tvName, tvMobileNo, tvSetting;
    private CircleImageView circleImageView;
    private ImageView imgHeaderProfile;
    private NavigationView navigationView;
    String name, image, userMobNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);

        tvName = headerView.findViewById(R.id.txt_profile_name);
        tvMobileNo = headerView.findViewById(R.id.txt_mobNo);
        circleImageView = headerView.findViewById(R.id.profile_imageView);
        imgHeaderProfile = headerView.findViewById(R.id.NevHeaderProfileBtn);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);     // By System Generated
        NavigationView navigationView = findViewById(R.id.nav_view); // By system genetated

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        imgHeaderProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        updateNavViewHeader();
    }


    private void updateNavViewHeader() {
        Intent intent = getIntent();
        image = HighwayPreface.getString(getApplicationContext(), "URL");
        name = HighwayPreface.getString(getApplicationContext(), Constants.NAME);
        userMobNo = HighwayPreface.getString(getApplicationContext(), Constants.USERMOBNO);

        tvName.setText(name);
        tvMobileNo.setText(userMobNo);

        if (!TextUtils.isEmpty(image)) {
            Picasso.with(this).load(image)
                    .error(R.drawable.logo_highway)
                    .into(circleImageView);
        } else {
            Picasso.with(this)
                    .load(R.drawable.logo_highway)
                    .error(R.drawable.logo_highway)
                    .into(circleImageView);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_new_booking:
                fragment = NewBookingFragmentMapActivity.newInstance();
                replaceFragment(fragment);
                break;

            case R.id.nav_my_booking:
                //  getSupportFragmentManager().beginTransaction().replace(R.id.ContaintMain,new DashBoardFragment()).commit();
                fragment = DashBoardFragment.newInstance();
                replaceFragment(fragment);
                break;

            case R.id.nav_track_your_booking:
                break;
            case R.id.nav_wallet:
                break;
            case R.id.nav_setting:
                break;
            case R.id.nav_notification:
                break;
            case R.id.nav_rate_card:
                break;
            case R.id.nav_help:
                break;
            case R.id.nav_about:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //kar raha hu sir click
    private void replaceFragment(Fragment fragment) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.ContaintMain, fragment, "");
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public void logout() {
        HighwayPreface.putBoolean(MainActivity.this, Constants.LoginCheck, false);
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }*/

}