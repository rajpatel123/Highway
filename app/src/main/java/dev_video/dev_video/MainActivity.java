package dev_video.dev_video;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import adapter.ViewPagerAdapter;
import fagments.DashboardFragment;
import fagments.HomeFragment;
import fagments.NotificationsFragment;
import fagments.ProfileFragment;
import fagments.Video;
import shared_preferences.AlertDialogManager;
import shared_preferences.SessionManager;

import static android.graphics.Color.RED;

public class MainActivity extends AppCompatActivity {

 BottomNavigationView bottomNavigationView;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
    // Session Manager Class
    SessionManager session;
    // Button Logout
    Button btnLogout;
    ImageButton dash;
    ImageButton home;
    ImageButton well;
    ImageButton acc;
    private RadioGroup recordSelectionRadioGroup;
    private RadioButton selectedRadioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dash = findViewById(R.id.deshbord);
        home = findViewById(R.id.follow);
        well = findViewById(R.id.notification);
        acc = findViewById(R.id.account);


        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.main_activity, new DashboardFragment());
        tx.commit();

    }

    public void selectFrag(View view) {
        Fragment fr = new DashboardFragment();
        if (view == findViewById(R.id.deshbord)) {

            dash.setImageDrawable(getResources().getDrawable(R.drawable.red_home));
           // dash.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_black_24dp));
            fr = new DashboardFragment();

        } else if (view == findViewById(R.id.follow)) {
            home.setImageDrawable(getResources().getDrawable(R.drawable.red_noti));
          // home.setImageDrawable(getResources().getDrawable(R.drawable.ic_dashboard_black_24dp));
            fr = new HomeFragment();
        } else if (view == findViewById(R.id.ve)) {
            //fr=new Video();
            showDialog();

        } else if (view == findViewById(R.id.notification)) {
            well.setImageDrawable(getResources().getDrawable(R.drawable.red_well));
           // well.setImageDrawable(getResources().getDrawable(R.drawable.ic_notifications_black_24dp));
            fr = new NotificationsFragment();
        } else if (view == findViewById(R.id.account)) {
            acc.setImageDrawable(getResources().getDrawable(R.drawable.red_acc));
            //acc.setImageDrawable(getResources().getDrawable(R.drawable.pro));
            fr = new ProfileFragment();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.main_activity, fr);
        tx.commit();

        session = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(), "User Login Status: "
                + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);

//
//        loadFragment(new HomeFragment());
//
//         BottomNavigationView navigation = findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(this);
    }

    public void showDialog(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.record_video_time);
        //dialog.setTitle("Your Title goes here");

        Button dialogButton = (Button) dialog.findViewById(R.id.button_ok);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordSelectionRadioGroup = dialog.findViewById(R.id.record_video_radio_group);
                int selectedId = recordSelectionRadioGroup.getCheckedRadioButtonId();

                selectedRadioButton = dialog.findViewById(selectedId);
                Toast.makeText(MainActivity.this,selectedRadioButton.getText().toString(),Toast.LENGTH_SHORT).show();

                int time = 0;

                if(selectedRadioButton.getText().toString().equals("30 Seconds"))
                    time = 30;
                else if(selectedRadioButton.getText().toString().equals("60 Seconds"))
                    time = 60;
                else if(selectedRadioButton.getText().toString().equals("90 Seconds"))
                    time = 90;

                Intent intent=new Intent(MainActivity.this,CamActivity.class);
                startActivity(intent);

                dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }



//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        Fragment fragment = null;
//        Fragment currentFragment =
//                getSupportFragmentManager().
//                        findFragmentById(R.id.container);
//
//
//        switch (item.getItemId()) {
//            case R.id.navigation_home:
//                fragment = new HomeFragment();
//                break;
//
//            case R.id.navigation_dashboard:
//
//            fragment = new DashboardFragment();
//                break;
//            case R.id.video:
//
//
//                fragment = new Video();
//                break;
//            case R.id.navigation_notifications:
//
//                fragment = new NotificationsFragment();
//                break;
//
//            case R.id.navigation_profile:
//
//
//                fragment = new ProfileFragment();
//                break;
//
//        }
////public void switchToFragment1() {
////    FragmentManager manager = getSupportFragmentManager();
////    manager.beginTransaction().replace(R.id.your_fragment_layout_name, new Fragment1()).commit();
////}
//        return loadFragment(fragment);    }
//    private boolean loadFragment(Fragment fragment) {
//        //switching fragment
//        if (fragment != null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, fragment)
//                    .commit();
//            return true;
//        }
//        return false;
//    }
}
