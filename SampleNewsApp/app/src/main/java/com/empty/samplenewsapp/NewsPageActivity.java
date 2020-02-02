package com.empty.samplenewsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewsPageActivity extends AppCompatActivity {

    public TextView TextView_title;
    public TextView TextView_description;
    public ImageView ImageView_title;

    public NewsData newsData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);

        TextView_title = findViewById(R.id.TextView_title);
        TextView_description = findViewById(R.id.TextView_description);
        ImageView_title = findViewById(R.id.ImageView_title);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        newsData = (NewsData) bundle.getSerializable("news");
        TextView_title.setText(newsData.getTitle());
        TextView_description.setText(newsData.getDescription());
        Uri uri = Uri.parse(newsData.getUrlToImage());
        ImageView_title.setImageURI(uri);


    }
}
