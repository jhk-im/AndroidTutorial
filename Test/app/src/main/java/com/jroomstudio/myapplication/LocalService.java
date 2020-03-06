package com.jroomstudio.myapplication;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class LocalService extends Service {

    IBinder mBinder = new LocalBinder();

    /**
     * 클라이언트가 액세스 할 수 있는 클래스.
     * 클라이언트와 동일한 프로세스에서 실행됨
     * IPC
     **/
    class LocalBinder extends Binder {
        LocalService getService(){
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    int getRan(){
        return  new Random().nextInt();
    }


}
