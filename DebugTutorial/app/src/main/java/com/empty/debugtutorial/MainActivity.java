package com.empty.debugtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView = findViewById(R.id.TextView);

        // 디버깅의 방법
        // 1. 시작부터 디버깅을 한다.
        // Debug app --> 상위 Run 버튼 옆에 벌레모양
        // 클릭하면 아래 빨간색으로 체크한 부분에서 멈춘다.
        // 아래 디버깅 창에서 화살표 버튼이 있다.
        // step over -> 한줄씩 내려간다. 현재 클래스에서만 확인한다.
        // step into -> 한줄씩 내려간다. 참조하는 다른 클래스가 나오면 해당 클래스로 이동해서 확인한다.


        String str = "123"; // <-  숫자옆 빈공간을 클릭
                            // 체크한 부분에서 멈춘다는 의미
        String str2 = "1234";
        if(str.equals(str2)){
            Log.d("true","123"); // log 는 초보적인 디버깅이다.
                                            // 안드로이드에서 제공하는 디버깅을 적극 활용하자.
        }else{
            Log.d("true","1234");
        }

        // 2. 특정 시점부터 디버깅을 한다.
        // Attach Debugger to Android Process --> 상위 벌레모양 + 화살표
        // SecondActivity 에 디버깅 체크를 해놨다면 처음부터 디버깅을 할 필요가없고
        // onClick 으로 액티비티가 넘어갈때 시작하면 된다.
        // 그때 사용하는 디버깅 방법이다 .
        // 먼저 앱을 실행하고 클릭을 하기전에 디버깅버튼을 누르고 클릭을 실행하면 된다.
        TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
