package com.empty.debugtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String str = "123"; // <-  숫자옆 빈공간을 클릭
        // 체크한 부분에서 멈춘다는 의미
        String str2 = "1234";
        if(str.equals(str2)){
            Log.d("true","11"); // log 는 초보적인 디버깅이다.
            // 안드로이드에서 제공하는 디버깅을 적극 활용하자.
        }else{
            Log.d("true","12");
        }

    }
}
