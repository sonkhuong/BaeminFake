package com.example.baeminfake.controller;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationApp extends Application {

    public static final String CHANNEL = "channel";

    @Override
    public void onCreate() {
        super.onCreate();
        this.createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL,
                    "Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is channel");

            NotificationManager manager = this.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }
}