package com.jroomstudio.blogupload.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.jroomstudio.blogupload.R;

public class LoginSecondActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name, email;
    Button logout;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_second);

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

        imageView = (ImageView) findViewById(R.id.imageView_user);
        name = (TextView) findViewById(R.id.textView_name);
        email = (TextView) findViewById(R.id.textView_email);
        logout = (Button) findViewById(R.id.button_logout);
        logout.setOnClickListener(v -> {
            switch (v.getId()){
                case R.id.button_logout:
                    signOut();
                    break;
            }
        });

        // 정보얻기
        // getLastSignedInAccount 메소드를 사용하여 현재 로그인한 사용자의 프로필을 요청할 수 있다.
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(acct != null){
            Glide.with(this).load(String.valueOf(acct.getPhotoUrl())).into(imageView);
            name.setText(acct.getDisplayName());
            email.setText(acct.getEmail());
            Log.v("id",acct.getId());
        }

    }

    // 로그아웃하는 메소드
    private void signOut(){
        mGoogleSignInClient.signOut().addOnCompleteListener(this, task ->
                        Toast.makeText(LoginSecondActivity.this,
                                "Sign Out!",
                                Toast.LENGTH_SHORT).show());
        finish();
    }
}
