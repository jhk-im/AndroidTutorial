package com.empty.sampleandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    // activity_login 의 email 과 password 인풋
    TextInputEditText textInputEditText_email, textInputEditText_password;
    // activity_login 로그인 버튼
    ConstraintLayout constraintLayout_login;

    // 임시로 입력된 값 비교하기 위한 변수
    String emailTemp = "123@gmail.com";
    String passwordTemp = "1234";

    // 사용자가 입력한 값
    String inputEmail ="";
    String inputPassword = "";

    // 자바의 main 메소드
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 현재 java 클래스는 activity_login.xml 과 연결되어 있다.
        setContentView(R.layout.activity_login);

        // activity_login 에서 실제 EditText 와 버튼 을 담아준다.
        textInputEditText_email = findViewById(R.id.TextInputEditText_email);
        textInputEditText_password = findViewById(R.id.TextInputEditText_password);
        constraintLayout_login = findViewById(R.id.ConstraintLayout_login);

        // 1-1. 값을 가져온다. - 검사 (123@gmail.com / 1234)
        // text 를 검사하여 일치할 때만 클릭 이벤트를 진행한다.
        // setClickable() -> 버튼 사용할 수 있는지 없는지 구분 -> 시각적효과
        // setEnabled() -> 버튼 객체를 활성화 / 비활성화 구분

        // login 버튼이 constraintLayout 으로 되어있다.
        // 그렇기 때문에 클릭할 수 있음을 사용자가 알 수 있도록 시각적 효과를 준다.
        constraintLayout_login.setClickable(true);
        // constraintLayout_login.setEnabled(true);
        // addTextChangedListener() -> EditText 의 변화 감지하는 메소드
        textInputEditText_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // toString() 메소드는 null 일경우 에러를 발생시키기 때문에
                // null 이 아닐경우에만 toString() 을 실행한다.
                if(s != null) {
                    inputEmail = s.toString();
                    //Log.d("SENTI",s+","+count);
                    constraintLayout_login.setEnabled(validation());
                    // 2. 클릭을 감지한다.
                    if(validation()){
                        onClickLogin();
                    }else{
                        constraintLayout_login.setClickable(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textInputEditText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.d("SENTI",s+","+count);

                // toString() 메소드는 null 일경우 에러를 발생시키기 때문에
                // null 이 아닐경우에만 toString() 을 실행한다.
                if(s != null) {
                    inputPassword = s.toString();
                    constraintLayout_login.setEnabled(validation());
                    // 2. 클릭을 감지한다.
                    if(validation()){
                        onClickLogin();
                    }else{
                        constraintLayout_login.setClickable(true);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    // 입력한 문자가 지정해둔 문자와 같은지 아닌지 판별 하는 함수 -> bool true, false 리턴
    public boolean validation(){
        return  inputEmail.equals(emailTemp) && inputPassword.equals(passwordTemp);
    }

    public void onClickLogin() {
        // 2. 클릭을 감지한다.
        constraintLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 1-2. 값을 가져온다.
                String email = textInputEditText_email.getText().toString();
                String password = textInputEditText_password.getText().toString();

                // 3. 1번의 값을 다른 액티비티로 넘긴다.
                // 값을 주고받는데 사용하는 하나의 규약 -> intent
                Intent intent = new Intent(MainActivity.this, LoginResultActivity.class);
                // putExtra() -> 전달 할 데이터를 Bundle 에 담는다.
                intent.putExtra("email",email);
                intent.putExtra("password",password);
                startActivity(intent);

            }
        });
    }
}
