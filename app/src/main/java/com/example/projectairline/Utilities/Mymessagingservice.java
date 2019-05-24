package com.example.projectairline.Utilities;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.projectairline.Main.Dashboard;
import com.example.projectairline.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Mymessagingservice extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        shownotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

    }


    public void shownotification(String title, String message) {
        Intent notifyIntent = new Intent(this, Dashboard.class);
// Set the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
// Create the PendingIntent
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "AirlineNotification")
                .setContentText(title)
                .setContentTitle(message)
                .setSmallIcon(R.drawable.appicon)
                .setAutoCancel(true);

        builder.setContentIntent(notifyPendingIntent);


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(999, builder.build());
    }

}
