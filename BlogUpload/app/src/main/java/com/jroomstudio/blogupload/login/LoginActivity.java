package com.jroomstudio.blogupload.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.jroomstudio.blogupload.R;


/**
 * 구글 문서
 * https://developers.google.com/identity/sign-in/android/start-integrating
 *
 *  참고 강의
 * https://www.youtube.com/watch?v=t-yZUqthDMM
 * https://www.youtube.com/watch?v=uPg1ydmnzpk
 *
**/
public class LoginActivity extends AppCompatActivity  {

    SignInButton signInButton;
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(v -> {
            switch (v.getId()){
                case R.id.sign_in_button:
                    signIn();
                    break;
            }
        });

        // 구글 로그인 구성
        // 사용자의 ID, 이메일 주소 및 기본 프로필을 요청하도록 로그인을 구성한다.
        // ID 및 기본 프로필을 DEFAULT_SIGN_IN 에 포함되어 있다.
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // GoogleSignInClient 를 빌드한다.
        // getClient 에 activity 와 gso 입력
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 사용자가 이미 로그인 하였는지 판별할 수 있다.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            // 로그인 되어있는 경우 아래 토스트 메세지를 띄운다.
            Toast.makeText(this, account.getEmail() + " 로그인", Toast.LENGTH_SHORT).show();
        }
    }

    // 로그인 진행
    // GoogleSignInClient 의 getSignInIntent() 메소드를 사용한다.
    private void signIn() {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    /**
     * 사용자가 로긍니 한 후 GoogleSignInAccount 활동의 onActivityResult 메소드에서
     * 사용자에 대한 오브젝트를 얻을 수 있다.
     **/
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    /**
     * GoogleSignInAccount 객체는 로그인한 사용자에 대한 정보가 포함되어있다.
     **/
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent intent = new Intent(LoginActivity.this,LoginSecondActivity.class);
            startActivity(intent);
        }catch (ApiException e){
            // 에러 감지
            Log.v("Error","signInResult:failed code="+ e.getStatusCode());
        }
    }

}
