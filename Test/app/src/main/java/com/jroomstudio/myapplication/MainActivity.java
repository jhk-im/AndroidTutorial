package com.jroomstudio.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Intent intent;
    //private Button btn_music_start,btn_music_stop;
    //private Button button;
    //private EditText editText;

/*
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
*/

    //private MyReceiver myReceiver;
/*
    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
                Toast.makeText(context, "Battery status is changed", Toast.LENGTH_SHORT).show();
            }else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
                Toast.makeText(context, "Screen ON", Toast.LENGTH_SHORT).show();
            }
        }
    };
*/

    private static final String PROVIDER_URI = "content://com.jroomstudio.myapplication.StudentProvider";
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("onCreate","Create!");
        /*
        intent = new Intent(this, MusicService.class);
        btn_music_start = (Button) findViewById(R.id.btn_music_start);
        btn_music_stop = (Button) findViewById(R.id.btn_music_stop);

        btn_music_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
                Log.i("서비스 테스트", "startService()");
            }
        });

        btn_music_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
                Log.i("서비스 테스트", "stopService()");
            }
        });
        */
       /*
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().equals("")){
                    intent = new Intent(getApplicationContext(),SecondActivity.class);
                    intent.putExtra("intent_text",editText.getText().toString());
                    startActivity(intent);
                }
            }
        });
        */
      /*
       Button btn_start = findViewById(R.id.button_start);
       Button btn_stop = findViewById(R.id.button_stop);
       Button btn_data = findViewById(R.id.button_data);
       Button btn_move = findViewById(R.id.button_move);

       btn_start.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, LocalService.class);
               bindService(intent,connection,Context.BIND_AUTO_CREATE);
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
               Intent secondIntent = new Intent(MainActivity.this,SecondActivity.class);
               startActivity(secondIntent);
           }
       });
        */
/*
      Button send = findViewById(R.id.button_send_broadcast);
      send.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              MyReceiver myReceiver = new MyReceiver();
              Intent intent = new Intent("action.test.BROADCAST_TEST");
              sendBroadcast(intent);

          }
      });
*/

        editText = findViewById(R.id.editText);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("onStart","Start!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume","Resume!");
/*
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(br,filter);


        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.test.BROADCAST_TEST");
        registerReceiver(myReceiver,intentFilter);
*/

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause","Pause!");
/*
        unregisterReceiver(br);
        unregisterReceiver(myReceiver);
*/

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("onStop","Stop!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy","Destroy!");
/*        if(isService){
            unbindService(connection);
            isService = false;
        }*/
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("onRestart","Restart!");
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_insert: {
                ContentValues addRowValue = new ContentValues();
                addRowValue.put("number","200106504");
                addRowValue.put("name","홍길동");
                addRowValue.put("department","컴퓨터");
                addRowValue.put("age","18");
                addRowValue.put("grade",3);
                getContentResolver().insert(Uri.parse(PROVIDER_URI),addRowValue);
                editText.setText("recode add");
                break;
            }
            case R.id.btn_query:{
                String[] columns = new String[]{"_id","number","name","department","age","grade"};
                Cursor c = getContentResolver().query(
                        Uri.parse(PROVIDER_URI),
                        columns,null,null,null,null);
                if(c != null){
                    editText.setText("");
                    while (c.moveToNext()){
                        int id = c.getInt(0);
                        String number = c.getString(1);
                        String name = c.getString(2);
                        String department = c.getString(3);
                        String age = c.getString(4);
                        int grade = c.getInt(5);
                        editText.append(
                                "id : "+id+"\n"
                                +"number : "+number+"\n"
                                +"name : "+name+"\n"
                                +"department : "+department+"\n"
                                +"age : "+age+"\n"
                                +"grade : "+grade+"\n");
                    }
                    editText.append("\n Total : " + c.getCount());
                    c.close();
                }
                break;
            }
            case R.id.btn_update:{
                ContentValues updateRowValue = new ContentValues();
                updateRowValue.put("name","고길동");
                int updateRecordCnt = getContentResolver().update(
                        Uri.parse(PROVIDER_URI),
                        updateRowValue,
                        "number=200106504",null);
                editText.setText("갱신 : " + updateRecordCnt);
                break;
            }
            case R.id.btn_delete:{
                int deleteRecordCnt = getContentResolver().delete(
                        Uri.parse(PROVIDER_URI),null,null);
                editText.setText("삭제 : " + deleteRecordCnt);
                break;
            }
        }
    }
}
