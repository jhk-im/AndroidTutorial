package com.empty.handlertutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView TextView_main;
    private int count = 0;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            count+=1;
            TextView_main.setText(count+""); // 1초간격
            // int 를 바로너면 에러가나서 끝에 "" String 추가

            if(count >=  5){ // 5가 되면 멈춤
                // 안만들어도 상관없으나 확인사살을 위해
                handler.removeCallbacks(runnable);
                TextView_main.setText("The End");
            }else{
                // 1초뒤에 재시작
                handler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView_main = findViewById(R.id.TextView_main);

        // Handler (runnable) - 카운트 증가 , 감소
        // Thread 쓰레드 : 작업공간 = 메인(UI 그리기) ... 백그라운드 ...

        // 시작을하면 핸들러가 runnable 을 1초 뒤에 실행시킨다.
        // handler.postDelayed(runnable, 1000);
        // 즉시시작
        handler.post(runnable); //-> runnable 의 run 안에있는 로직이 실행된다.

        // 자바처럼 Thread 사용가능 , 조금 더 안정적
        // Thread thread = new Thread();

        // 간단하게 사용할 때 Handler
    }
}
