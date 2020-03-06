package com.jroomstudio.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;

    LocalService mLocalService;
    boolean isService = false;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalService.LocalBinder mb = (LocalService.LocalBinder) service;
            mLocalService = mb.getService();
            isService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(getApplicationContext(), "서비스 연결 해제", Toast.LENGTH_SHORT).show();
            isService = false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.e("Second_onCreate","Second_Create!");

        /*
        textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("intent_text"));
        */

        Button btn_start = findViewById(R.id.button_start);
        Button btn_stop = findViewById(R.id.button_stop);
        Button btn_data = findViewById(R.id.button_data);
        Button btn_move = findViewById(R.id.button_move);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, LocalService.class);
                bindService(intent,connection, Context.BIND_AUTO_CREATE);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isService){
                    Toast.makeText(getApplicationContext(), "unbindService", Toast.LENGTH_SHORT).show();
                    unbindService(connection);
                    isService = false;
                } else {
                    Toast.makeText(getApplicationContext(), "isService - False", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isService){
                    Toast.makeText(getApplicationContext(), "isService - False", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = mLocalService.getRan();
                Toast.makeText(getApplicationContext(), "getData -> " + num, Toast.LENGTH_SHORT).show();
            }
        });

        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(secondIntent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy","Destroy!");
        if(isService){
            unbindService(connection);
            isService = false;
        }
    }

}
