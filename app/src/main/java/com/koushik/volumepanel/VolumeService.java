package com.koushik.volumepanel;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;

public class VolumeService extends Service {

    static final String ACTION_UP = "VOL_UP";
    static final String ACTION_DOWN = "VOL_DOWN";
    static final String ACTION_MUTE = "VOL_MUTE";

    static int lastVolume = 5;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1, buildNotification());
        return START_STICKY;
    }

    private Notification buildNotification() {
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int cur = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        int percent = (cur * 100) / max;

        PendingIntent down = PendingIntent.getBroadcast(
                this, 1,
                new Intent(this, VolumeReceiver.class).setAction(ACTION_DOWN),
                0
        );

        PendingIntent mute = PendingIntent.getBroadcast(
                this, 2,
                new Intent(this, VolumeReceiver.class).setAction(ACTION_MUTE),
                0
        );

        PendingIntent up = PendingIntent.getBroadcast(
                this, 3,
                new Intent(this, VolumeReceiver.class).setAction(ACTION_UP),
                0
        );

        return new Notification.Builder(this)
                .setContentTitle("Volume Controller")
                .setContentText("Media Volume: " + percent + "%")
                .setSmallIcon(android.R.drawable.ic_lock_silent_mode)
                .addAction(0, "-", down)
                .addAction(0, "x", mute)
                .addAction(0, "+", up)
                .setOngoing(true)
                .build();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
