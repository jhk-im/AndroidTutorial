package com.empty.samplenewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    // Instantiate the RequestQueue.
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        // 1. 화면 로딩 -> 뉴스 정보를 받아온다
        queue = Volley.newRequestQueue(this);
        getNews();

        // 2. 정보 -> 어댑터 념겨준다
        // 3. 어댑터 -> 셋팅

    }

    public void getNews(){

        String url ="https://newsapi.org/v2/top-headlines?country=kr&apiKey=912c7e0f839e4eaa86a8d48990b44be4";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Log.d("news",response);

                        try {

                            JSONObject jsonObj = new JSONObject(response);

                            JSONArray arrayArticles = jsonObj.getJSONArray("articles");

                            List<NewsData> news = new ArrayList<NewsData>();
                            for(int i = 0, j = arrayArticles.length(); i<j; i++){
                               JSONObject obj = arrayArticles.getJSONObject(i);
                                Log.d("news",obj.toString());
                                // response --> NewsData Class 로 분류
                                NewsData newsData = new NewsData();
                                newsData.setTitle(obj.getString("title"));
                                newsData.setDescription(obj.getString("description"));
                                newsData.setUrlToImage(obj.getString("urlToImage"));
                                news.add(newsData);
                            }

                            // specify an adapter (see also next example)
                            mAdapter = new MyAdapter(news, MainActivity.this, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Object obj = v.getTag();
                                    if(obj != null){
                                        int position = (int)obj;
                                        // 친자확인
                                        ((MyAdapter)mAdapter).getNews(position);
                                        Intent intent = new Intent(MainActivity.this, NewsPageActivity.class);
                                        // 1. 본문
                                        // 2. 전체
                                        // 2-1. 하나씩
                                        // 2-2. 한번에 다 넘기기

                                        intent.putExtra("news",((MyAdapter)mAdapter).getNews(position));
                                        startActivity(intent);
                                    }
                                }
                            });
                            recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
