package com.highway.broadCastReceiver;

import android.app.IntentService;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.highway.utils.Constants;


public class MyIntentService extends IntentService {

    public static  final String MY_SERVICE_INTENT = "MyServiceIntent";
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String data = intent.getStringExtra("key");

        try {
            Thread.sleep(4000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent resultIntent  = new Intent(Constants.PUSH_ACTION);
        resultIntent.putExtra("key","Driver Accepted Trip");
        LocalBroadcastManager.getInstance(getApplicationContext())
                .sendBroadcast(resultIntent);


    }

}
