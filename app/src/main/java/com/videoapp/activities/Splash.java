package com.videoapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.videoapp.R;
import com.videoapp.shared_preferences.SessionManager;

public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME = 4000; //This is 4 seconds
    SessionManager session;
    boolean login_status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        session = new SessionManager(getApplicationContext());

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        login_status=session.isLoggedIn();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(login_status)

                {
                    startActivity(new Intent(Splash.this, MainActivity.class));


                }else {
                    Intent mySuperIntent = new Intent(Splash.this, Login.class);
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
