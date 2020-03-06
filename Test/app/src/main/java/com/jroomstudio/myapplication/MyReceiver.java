package com.jroomstudio.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("action.test.BROADCAST_TEST")){
            Toast.makeText(context, "Custom Broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}
