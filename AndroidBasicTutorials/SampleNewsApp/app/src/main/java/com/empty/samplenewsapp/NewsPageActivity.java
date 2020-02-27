package com.empty.samplenewsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewsPageActivity extends AppCompatActivity {

    public TextView TextView_title; // 뉴스제목
    public TextView TextView_description; // 뉴스내용
    public ImageView ImageView_title; // 뉴스이미지

    public NewsData newsData; // NewsData 객체
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);

        TextView_title = findViewById(R.id.TextView_title);
        TextView_description = findViewById(R.id.TextView_description);
        ImageView_title = findViewById(R.id.ImageView_title);

        // getIntent() 로 MainActivity 에서 넘어온 데이터를 받느다.
        Intent intent = getIntent();
        // Bundle 로 넘어오기 때문에 Bundle 에 넘어온 데이터 전체를 담는다.
        Bundle bundle = intent.getExtras();
        // MainActivity 에서 넘긴 정보는 이름이 "news" 인 NewsData 객체이다.
        // bundle.getSerializable(name) 으로 객체를 찾고
        // newsData 에 담기위해 (NewsData) 로 형변환 해준다.
        newsData = (NewsData) bundle.getSerializable("news");

        // 이제 newsData 는 MainActivity 에서 클릭한 newsData 의 객체이다.
        // newsData 로 각각 title,description, Image 를 셋 해주면 마무리된다.
        TextView_title.setText(newsData.getTitle());
        TextView_description.setText(newsData.getDescription());
        // setImageURI 에는 입력값으로 Uri 객체를 넣어주어야한다.
        // 다음은 받아온 url 을 Uri 객체로 형변환 해주는 로직이다.
        // Uri 는 프레스코에서 지원하는 기능이다.
        Uri uri = Uri.parse(newsData.getUrlToImage());
        ImageView_title.setImageURI(uri); // 해당 Uri 의 이미지가 출력된다.


    }
}
