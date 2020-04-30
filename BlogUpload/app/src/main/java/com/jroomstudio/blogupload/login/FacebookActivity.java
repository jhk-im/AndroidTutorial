package com.jroomstudio.blogupload.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.jroomstudio.blogupload.R;

import java.util.Arrays;

/**
 * 페이스북 문서
 * https://developers.facebook.com/docs/facebook-login/android
 *
 * 참고
 * https://lakue.tistory.com/12
 **/

public class FacebookActivity extends AppCompatActivity {

    LoginButton loginButton;
    FacebookLoginCallback mLoginCallback;
    CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        mCallbackManager = CallbackManager.Factory.create();
        mLoginCallback = new FacebookLoginCallback();

        loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        loginButton.setPermissions(Arrays.asList("public_profile", "email"));
        loginButton.registerCallback(mCallbackManager, mLoginCallback);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
