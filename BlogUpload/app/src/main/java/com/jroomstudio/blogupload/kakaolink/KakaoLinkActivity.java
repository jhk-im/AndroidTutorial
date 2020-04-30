package com.jroomstudio.blogupload.kakaolink;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jroomstudio.blogupload.R;

public class KakaoLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_link);

        KakaoLink kakaoLink = new KakaoLink(getApplicationContext());

        Button linkButton = (Button) findViewById(R.id.link_button);
        linkButton.setOnClickListener(v ->
        {
            kakaoLink.kakaolink();
            Toast.makeText(this, "link", Toast.LENGTH_SHORT).show();
        }
        );

    }
}
