package com.example.mathematicstraining;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFMService";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i("MyFirebaseService"," onNewToken token "+s);

    }

    private void sendTokenToRegisterOnServer(String s) {
        // Handle your logic
        Log.d(TAG, "Token : "+s);
        sendTokenToRegisterOnServer(s);
    }
/*
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
            // Handle data payload of FCM messages.
            Log.d(TAG, "FCM Message Id: " + remoteMessage.getMessageId());
            Log.d(TAG, "FCM Notification Message: " + remoteMessage.getNotification());
            Log.d(TAG, "FCM Data Message: " + remoteMessage.getData());

            PendingIntent mPendingIntent = PendingIntent.getActivities(getApplicationContext(), 100,
                    new Intent[]{new Intent(getApplicationContext(), MainActivity.class)}, PendingIntent.FLAG_ONE_SHOT);
            createNotification(remoteMessage.getNotification().getTitle(),
                    remoteMessage.getNotification().getBody(), mPendingIntent);
        }


        public void createNotification(String title, String content, @Nullable PendingIntent pendingIntent) {
            Uri soundUri = Uri.parse("android.resource://"
                    + getPackageName() + "/" + R.raw.granules);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "default");
            builder.setAutoCancel(true);
            builder.setSound(soundUri);
            builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
            builder.setContentTitle(title);
            builder.setContentText(content);
            if (pendingIntent != null) {
                builder.setContentIntent(pendingIntent);
            }
            builder.setSmallIcon(R.mipmap.ic_launcher);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(200, builder.build());
        }
*/
public void createNotification(String title, String content, @Nullable PendingIntent pendingIntent) {
    Uri soundUri = Uri.parse("android.resource://"
            + getPackageName() + "/" + R.raw.granules);
    Log.i("MyFirebaseService"," createNotification");
    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "default");
    builder.setAutoCancel(true);
    builder.setSound(soundUri);
    builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
    builder.setContentTitle(title);
    builder.setContentText(content);
    if (pendingIntent != null) {
        builder.setContentIntent(pendingIntent);
    }
    builder.setSmallIcon(R.mipmap.ic_launcher);

    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    notificationManager.notify(200, builder.build());
}

@Override
public void onMessageReceived(RemoteMessage remoteMessage) {
    super.onMessageReceived(remoteMessage);

    if (remoteMessage.getData()!=null) {
        Log.i("MyFirebaseService","title "+remoteMessage.getNotification().getTitle());
        Log.i("MyFirebaseService","body "+remoteMessage.getNotification().getBody());
    }
   // sendNotification(remoteMessage.getData().get("title"),remoteMessage.getData().get("msg"));
    PendingIntent mPendingIntent = PendingIntent.getActivities(getApplicationContext(), 100,
            new Intent[]{new Intent(getApplicationContext(), MainActivity.class)}, PendingIntent.FLAG_ONE_SHOT);
    createNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(), mPendingIntent);
}

    private void sendNotification(String messageTitle,String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = "default_notification_channel_id";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setContentTitle(messageTitle)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);
        Log.i("MyFirebaseService","sendNotification 1111");
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Log.i("MyFirebaseService","sendNotification 2222");
        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        Log.i("MyFirebaseService","sendNotification 3333");
        notificationManager.notify(0 , notificationBuilder.build());
        Log.i("MyFirebaseService","sendNotification 4444");
    }

}