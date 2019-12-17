package com.highway.common.base.firebaseService;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.highway.R;
import com.highway.common.base.activity.LoginOptionActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MyFirebaseServiceMessaging extends FirebaseMessagingService {


    private static final String TAG = MyFirebaseServiceMessaging.class.getSimpleName();
    public static final String ACTION_1 = "action_1";
    String click_action;

    String message;
    int x;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, remoteMessage.getData().toString() + "From: " + remoteMessage.getFrom());
      //  click_action = remoteMessage.getNotification().getClickAction();
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("payload", "Message data payload: " + remoteMessage.getData());

            /* message = String.valueOf(remoteMessage.getData());*/
            JSONObject obj = new JSONObject(remoteMessage.getData());
            try {
                x = obj.getInt("challenge_id");
                Log.d("data", "data" + x);
                //Toast.makeText(this, ""+x, Toast.LENGTH_SHORT).show();



            } catch (JSONException e) {
                e.printStackTrace();
            }


            sendBackgroundForegroundNotification(remoteMessage.getData());
        }
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        }
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    /**
     * Handle Background and Foreground Notifications
     *
     * @param message A Map with key value pair that hold
     *                information regarding pending Intent that navigate to corresponding  screen
     */
    private void sendBackgroundForegroundNotification(Map<String, String> message) {


        /*Shown notification only when you have data object model*/


            try {

              Intent intent = new Intent(this, LoginOptionActivity.class);
                intent.putExtra("challenegid", x);
              Toast.makeText(this, "" + x, Toast.LENGTH_SHORT).show();


                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder notificationBuilder;

                /*check for  oreo check  for notification builder */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel notificationChannel = new NotificationChannel("ID", "Name", importance);
                    notificationManager.createNotificationChannel(notificationChannel);
                    notificationBuilder = new NotificationCompat.Builder(this, notificationChannel.getId());
                } else
                    notificationBuilder = new NotificationCompat.Builder(this, null);

                notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.about_image))
                        .setContentTitle("New challenge")
                        .setContentText("you get new Challenge")
                        .setSubText(DateUtils.getRelativeTimeSpanString(this, System.currentTimeMillis()))
                        .setAutoCancel(true)
                        .addAction(new NotificationCompat.Action(R.drawable.circle_icon,
                                "Action 1", pendingIntent))

                        .setSound(defaultSoundUri)
                         // set Style for large text notification
                        .setContentIntent(pendingIntent);

                /**
                 * Add notification small transparent icon
                 * ***/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    notificationBuilder.setSmallIcon(R.drawable.circle_icon);
                else
                    notificationBuilder.setSmallIcon(R.drawable.circle_icon);

                if (notificationManager != null) {

                    notificationManager.notify("My Voice DataModel", (int) System.currentTimeMillis()
                            /*ID of notification */, notificationBuilder.build());

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }




