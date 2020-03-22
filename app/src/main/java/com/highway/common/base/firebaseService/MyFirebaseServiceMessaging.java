package com.highway.common.base.firebaseService;

/**
 * Created by santhosh@appoets.com on 21-05-2018.
 */

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.highway.BuildConfig;
import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utilities;

import org.json.JSONObject;

import static com.highway.utils.Constants.COMPLETED;
import static com.highway.utils.Constants.DROPPED;
import static com.highway.utils.Constants.INVOICE;
import static com.highway.utils.Constants.PICKEDUP;
import static com.highway.utils.Constants.RATING;
import static com.highway.utils.Constants.SEARCHING;
import static com.highway.utils.Constants.TRIP_ACCEPTED;
import static com.highway.utils.Constants.TRIP_CANCELED;
import static com.highway.utils.Constants.TRIP_NEW;
import static com.highway.utils.Constants.TRIP_REJECTED;
import static com.highway.utils.Constants.TRIP_STARTED;

public class MyFirebaseServiceMessaging extends FirebaseMessagingService {
    private LocalBroadcastManager broadcaster;
    int notificationId = 0;
    OnMessageRecievedFromPush onMessageRecievedFromPush;
    private final String TAG = getClass().getSimpleName();
    public static final String INTENT_FILTER = "INTENT_FILTER" + BuildConfig.APPLICATION_ID;

    @Override
    public void onNewToken(String s) {
        @SuppressLint("HardwareIds")
        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        Log.d("DEVICE_ID: ", deviceId);
        Log.d("FCM_TOKEN", s);

        HighwayPrefs.putString(this, "device_token", s);
        HighwayPrefs.putString(this, "device_id", deviceId);
    }


    @Override
    public void onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this);
    }

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (!HighwayPrefs.getBoolean(this, Constants.LOGGED_IN)) {
            return;
        }

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Fromdata: " + remoteMessage.getData());
        JSONObject jsonObject = new JSONObject();

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
//            sendNotification(remoteMessage.getData().get("message"));
            try {
                String type = remoteMessage.getData().get("type");

                switch (type) {
                    case Constants.TRIP_NEW:

                        jsonObject.put(Constants.PUSH_TYPE, type);
                        jsonObject.put(Constants.CUSTOMER_NAME, remoteMessage.getData().get("customer"));
                        jsonObject.put(Constants.PUSH_MOBILE, remoteMessage.getData().get("mobile"));
                        jsonObject.put(Constants.TRIP_ID, remoteMessage.getData().get("tripId"));
                        jsonObject.put(Constants.SOURCE, remoteMessage.getData().get("source"));
                        jsonObject.put(Constants.DESTINATEION, remoteMessage.getData().get("destination"));
                        sendNotification(jsonObject, type,"New Ride coming");

                        break;

                    case TRIP_ACCEPTED:
                        jsonObject.put(Constants.PUSH_TYPE, type);
                        jsonObject.put(Constants.DRIVER_NAME, remoteMessage.getData().get("driver"));
                        jsonObject.put(Constants.PUSH_MOBILE, remoteMessage.getData().get("mobile"));
                        jsonObject.put(Constants.TRIP_ID, remoteMessage.getData().get("tripId"));
                        jsonObject.put(Constants.VEHICLE_TYPE, remoteMessage.getData().get("vehicleType"));
                        jsonObject.put(Constants.VEHICLE_NUMBER, remoteMessage.getData().get("vehicleNumber"));
                        jsonObject.put(Constants.VEHICLE_IMAGE, remoteMessage.getData().get("vehicleImage"));

                        sendNotification(jsonObject, type,"Your trip assigned to a driver");

                        break;

                        case TRIP_REJECTED:
                        jsonObject.put(Constants.PUSH_TYPE, type);
                        sendNotification(jsonObject, type,"Your trip assigned to a driver");

                        break;

                        case TRIP_CANCELED:
                        jsonObject.put(Constants.PUSH_TYPE, type);
                        sendNotification(jsonObject, type,"Your trip has been cancelled by  a driver");

                        break;
                    case TRIP_STARTED:
                        jsonObject.put(Constants.PUSH_TYPE, type);

                        sendNotification(jsonObject, type,"You are on your way!");

                        break;
                    case Constants.ARRIVED:
                        jsonObject.put(Constants.PUSH_TYPE, type);

                        sendNotification(jsonObject, type,"I have arrived!");

                        break;
                    case PICKEDUP:
                        jsonObject.put(Constants.PUSH_TYPE, type);

                        sendNotification(jsonObject, type,"You are on your way!");

                        break;
                    case DROPPED:
                        jsonObject.put(Constants.PUSH_TYPE, type);
                        sendNotification(jsonObject, type,"Dropped!");

//                        sendNotification(jsonObject, type,"Thanks for booking with Highway, it was a great trip with you");

                        break;
                    case COMPLETED:
                        jsonObject.put(Constants.PUSH_TYPE, type);
                        sendNotification(jsonObject, type,"Trip Completed");

                        break;
                    case RATING:
                        jsonObject.put(Constants.PUSH_TYPE, type);
                        sendNotification(jsonObject, type,"Rating done");

                        break;
                    case INVOICE:
                        jsonObject.put(Constants.PUSH_TYPE, type);
                        sendNotification(jsonObject, type,"Payment Received");

                        break;


                }


            } catch (Exception e) {
                Log.e("Exception: ", e.getMessage());
            }
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]


    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     */


    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void sendNotification(JSONObject jsonObject, String type,String tittle) {
        String messageBody = jsonObject.toString();
        if (!Utilities.isAppIsInBackground(getApplicationContext())) {

            if (type.equalsIgnoreCase(TRIP_NEW) && HighwayPrefs.getString(getApplicationContext(), Constants.ROLEID).equalsIgnoreCase("4")) {
                return;
            }
            Intent intent = new Intent("MyData");
            intent.putExtra(Constants.PUSH_NEW_BOOKING_TRIP_DATA_KEY, jsonObject.toString());

            broadcaster.sendBroadcast(intent);


        } else {

            if (type.equalsIgnoreCase(TRIP_NEW) && HighwayPrefs.getString(getApplicationContext(), Constants.ROLEID).equalsIgnoreCase("4")) {
                return;
            }
            Utilities.printV(TAG, "background");
            Intent mainIntent = new Intent(this, DashBoardActivity.class);
            mainIntent.putExtra(Constants.PUSH_NEW_BOOKING_TRIP_DATA_KEY, jsonObject.toString());
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainIntent);


        }

        // NotificationPushData data = BaseUtil.objectFromString(messageBody, NotificationPushData.class);
        Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //intent.putExtra(Constants.PUSH_NEW_BOOKING_TRIP_DATA_KEY, data);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

//        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
//        Intent localIntent = new Intent("CUSTOM_ACTION");
//        // Send local broadcast
//        localBroadcastManager.sendBroadcast(localIntent);
//        localBroadcastManager.sendBroadcast(intent);
//        PushNavigateReceiver receiver = new PushNavigateReceiver();
//        IntentFilter intentFilter = new IntentFilter("custom.notification.navigation");
//        registerReceiver(receiver, intentFilter);
        Intent filter = new Intent("custom.notification.navigation");
        sendBroadcast(filter);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "PUSH");
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine(messageBody);

        long when = System.currentTimeMillis();         // notification time


        // Sets an ID for the notification, so it can be updated.
        int notifyID = 1;
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        CharSequence name = "Channel human readable title";// The user-visible name of the channel.
        int importance = NotificationManager.IMPORTANCE_HIGH;


        Notification notification;
        notification = mBuilder.setSmallIcon(R.mipmap.ic_launcher).setTicker(getString(R.string.app_name)).setWhen(when)
//                .setAutoCancel(true)
                .setContentTitle(getString(R.string.app_name))
                .setContentIntent(pendingIntent)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(tittle))
                .setWhen(when)
                .setSmallIcon(getNotificationIcon(mBuilder))
                .setContentText(messageBody)
                .setChannelId(CHANNEL_ID)
                .setDefaults(Notification.DEFAULT_VIBRATE
                        | Notification.DEFAULT_LIGHTS
                )
                .build();

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            android.app.NotificationChannel mChannel = new android.app.NotificationChannel(CHANNEL_ID, name, importance);
            // Create a notification and set the notification channel.
            notificationManager.createNotificationChannel(mChannel);
        }

        notificationManager.notify(notifyID, notification);
    }

    private int getNotificationIcon(NotificationCompat.Builder notificationBuilder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
            return R.drawable.ic_stat_ic_notification;
        } else {
            return R.mipmap.ic_launcher;
        }
    }


    public void setPushListener(OnMessageRecievedFromPush onMessageRecievedFromPush) {
        this.onMessageRecievedFromPush = onMessageRecievedFromPush;
    }


    public interface OnMessageRecievedFromPush {
        void onPushData(JSONObject jsonObject);
    }

}
