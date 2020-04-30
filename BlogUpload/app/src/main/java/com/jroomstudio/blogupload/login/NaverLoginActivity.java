package com.jroomstudio.blogupload.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jroomstudio.blogupload.R;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import java.lang.ref.WeakReference;

/**
 * 네이버 문서
 * https://developers.naver.com/docs/login/android/
 *
 * 참고 *
 * https://altongmon.tistory.com/433
 * https://sodp2.tistory.com/3
 * https://hoyi327.tistory.com/20
 **/

public class NaverLoginActivity extends AppCompatActivity {

    OAuthLoginButton mOAuthLoginButton;
    public static OAuthLogin mOAuthLoginModule;
    private OAuthLoginHandler mOAuthLoginHandler = new NaverLoginHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver_login);

        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                this,
                "hYU0kQ0W3w2VnRBgEUAo",
               "wpPHo8fHDh",
                "jroom");

        mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.button_naverlogin);
        mOAuthLoginButton.setOnClickListener(v ->
                mOAuthLoginModule.
                        startOauthLoginActivity(NaverLoginActivity.this,mOAuthLoginHandler)
        );

    }



    private static class NaverLoginHandler extends OAuthLoginHandler {
        private final WeakReference<NaverLoginActivity> mActivity;

        public NaverLoginHandler(NaverLoginActivity activity) {
            mActivity = new WeakReference<NaverLoginActivity>(activity);
        }


        @Override
        public void run(boolean success) {
            NaverLoginActivity activity = mActivity.get();

            if (success) {
                String accessToken = mOAuthLoginModule.getAccessToken(activity);
                String refreshToken = mOAuthLoginModule.getRefreshToken(activity);
                long expiresAt = mOAuthLoginModule.getExpiresAt(activity);
                String tokenType = mOAuthLoginModule.getTokenType(activity);
            } else {
                String errorCode = mOAuthLoginModule.getLastErrorCode(activity).getCode();
                String errorDesc = mOAuthLoginModule.getLastErrorDesc(activity);
                Toast.makeText(activity, "errorCode:" + errorCode
                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
