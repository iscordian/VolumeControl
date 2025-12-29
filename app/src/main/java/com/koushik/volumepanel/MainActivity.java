package com.koushik.volumepanel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        startService(new Intent(this, VolumeService.class));
        finish();
    }
}
