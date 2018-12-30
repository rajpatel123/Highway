package dev_video.dev_video;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import shared_preferences.AlertDialogManager;
import shared_preferences.SessionManager;
import utill.RuntimePermissionHelper;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText emails, password;
    String email_str, pass_str;
    Button btn_login_;
    TextView signup_text_;
    private RuntimePermissionHelper runtimePermissionHelper;
    private int count = 0;
    private static final String LoginUrl = " http://akwebtech.com/dev/api/api.php?req=login ";
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emails = findViewById(R.id.useremail);
        password = findViewById(R.id.userpassword);
        btn_login_ = findViewById(R.id.btnUserlogin);
        signup_text_ = findViewById(R.id.usersingup);


        //shared preferences
        session = new SessionManager(getApplicationContext());

        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


        btn_login_.setOnClickListener(this);
        signup_text_.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= 23) {
            runtimePermissionHelper = RuntimePermissionHelper.getInstance(this);
            if (runtimePermissionHelper.isAllPermissionAvailable()) {
// All permissions available. Go with the flow
            } else {
// Few permissions not granted. Ask for ungranted permissions
                runtimePermissionHelper.setActivity(this);
                runtimePermissionHelper.requestPermissionsIfDenied();
            }
        } else {
// SDK below API 23. Do nothing just go with the flow.
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean permissionGranted = true;
        for (int i : grantResults) {
            if (i != PackageManager.PERMISSION_GRANTED) {
                permissionGranted = false;
                break;
            }
        }
        if (!permissionGranted) {
            if (count > 4) {
                Toast.makeText(this, R.string.location_permission_toast, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + BuildConfig.APPLICATION_ID)));
            }
            runtimePermissionHelper.requestPermissionIfDenied(RuntimePermissionHelper.PERMISSION_ACCESS_FINE_LOCATION);
            runtimePermissionHelper.requestPermissionIfDenied(RuntimePermissionHelper.PERMISSION_ACCESS_CAMERA);
            runtimePermissionHelper.requestPermissionIfDenied(RuntimePermissionHelper.PERMISSION_ACCESS_WRITE_EXTERNAL_STORAGE);

        }
        count++;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnUserlogin) {
            email_str = emails.getText().toString();
            pass_str = password.getText().toString();


            if (email_str.equals("admin@gmail.com") || pass_str.equals("admin")) {

                session.createLoginSession("Astu_pvt", "astu@gmail.com");

                startActivity(new Intent(Login.this, MainActivity.class));
                finish();

            } else {
                emails.setError("please fill vaild email");
                alert.showAlertDialog(Login.this, "Login failed..", "Username/Password is incorrect", false);
            }
        } else if (v.getId() == R.id.usersingup) {
            startActivity(new Intent(Login.this, Signup.class));
        }
    }
}
