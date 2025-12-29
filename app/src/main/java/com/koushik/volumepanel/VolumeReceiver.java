package com.koushik.volumepanel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public class VolumeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context c, Intent i) {
        AudioManager am = (AudioManager) c.getSystemService(Context.AUDIO_SERVICE);

        if (VolumeService.ACTION_UP.equals(i.getAction())) {
            am.adjustVolume(AudioManager.ADJUST_RAISE, 0);
        }

        if (VolumeService.ACTION_DOWN.equals(i.getAction())) {
            am.adjustVolume(AudioManager.ADJUST_LOWER, 0);
        }

        if (VolumeService.ACTION_MUTE.equals(i.getAction())) {
            int cur = am.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (cur > 0) {
                VolumeService.lastVolume = cur;
                am.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            } else {
                am.setStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        VolumeService.lastVolume,
                        0
                );
            }
        }

        c.startService(new Intent(c, VolumeService.class));
    }
}
