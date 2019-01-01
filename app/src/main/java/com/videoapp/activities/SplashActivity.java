package com.videoapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.videoapp.R;
import com.videoapp.utill.AppConstants;
import com.videoapp.utill.VideoAppPrefs;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME = 4000; //This is 4 seconds
    boolean login_status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Toast.makeText(getApplicationContext(), "User LoginActivity Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!TextUtils.isEmpty(VideoAppPrefs.getString(getApplicationContext(), AppConstants.CUSTOMER_ID)))
                {
                    startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                }else {
                    Intent mySuperIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mySuperIntent);
                }




                //Do any action here. Now we are moving to next page

                /* This 'finish()' is for exiting the app when back button pressed
                 *  from Home page which is ActivityHome
                 */
                finish();
            }
        }, SPLASH_TIME);
    }

}
