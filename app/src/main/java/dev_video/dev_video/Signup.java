package dev_video.dev_video;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.ion.Ion;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    EditText username,userpassword,useremail,usercontact;
    Button btnRegister;
    private static final String registerUrl="http://akwebtech.com/dev/api/api.php?req=registration";
    TextView link_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username=findViewById(R.id.regUserName);
        userpassword=findViewById(R.id.regUserpassword);
        useremail=findViewById(R.id.regUserEmail);
        usercontact=findViewById(R.id.regUserContactNo);
        btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resigaternewuser();
            }
        });
    }
    private void Resigaternewuser(){
        StringRequest request=new StringRequest(Request.Method.POST, registerUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("Insert Successfully")){
                    Toast.makeText(getApplicationContext(), "sucessfully Regisatered user", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Signup.this,Login.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "error Regisatered user", Toast.LENGTH_LONG).show();
                }}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hereiserror", error+"");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> Params=new HashMap<>();
                //Params.put("registeruser","true");
                Params.put("name",username.getText().toString().trim());
                Params.put("email_id",useremail.getText().toString().trim());
                Params.put("mobile_no",usercontact.getText().toString().trim());
                Params.put("password",userpassword.getText().toString().trim());
                return Params;
            }
        };
        Volley.newRequestQueue(this).add(request);


        link_login=findViewById(R.id.link_login);

        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login=new Intent(Signup.this,Login.class);
                startActivity(login);
            }
        });



    }
}
