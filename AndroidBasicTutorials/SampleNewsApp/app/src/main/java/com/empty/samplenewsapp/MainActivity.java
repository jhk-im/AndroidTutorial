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

/*
* AndroidManifests.xml
* ->     <uses-permission android:name="android.permission.INTERNET" />
* ->         <activity android:name=".NewsPageActivity"></activity>
*
* build.gradle (Module.app)
* ->implementation 'com.android.support:recyclerview-v7:28.0.0'
    implementation 'com.android.support:cardview-v7:28.0.0'
    implementation 'com.android.support:design:28.0.0'
    implementation 'com.facebook.fresco:fresco:2.1.0'
* */

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView; // activity_news 의 리사이클러뷰
    private RecyclerView.Adapter mAdapter; // 리사이클러뷰를 사용하기 위한 어댑터
    private RecyclerView.LayoutManager layoutManager; // 리사이클러뷰를 사용하기 위한 레이아웃 매니져

    // Instantiate the RequestQueue.
    // Volley 는 응답받은 내용을 RequestQueue 객체에 담는다.
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
        // queue 에 현재 activity 에서
        // Volley 를 사용해 정보를 요청하고 응답받은 내용은 queue 에 담긴다.
        queue = Volley.newRequestQueue(this);
        // 요청시작
        getNews();

        // 2. 정보 -> 어댑터 념겨준다
        // 3. 어댑터 -> 셋팅

    }

    // ************
    // 뉴스 받아오기 https://newsapi.org/s/south-korea-news-api
    public void getNews(){

        // 1. 요청하기
        // volley
        // https://developer.android.com/training/volley/simple.html
        // 정보 요청
        // http 를 활용하여 해당 url 로부터 정보를 받아온다.
        String url ="https://newsapi.org/v2/top-headlines?country=kr&apiKey=912c7e0f839e4eaa86a8d48990b44be4";

        // Request a string response from the provided URL.
        // 응답으로 string 을 받아온다.
        // Request.Method.GET 메소드와 위에 지정한 url 정보를 입력하고 요청한다.
        // String response 에 요청한 정보가 담긴다.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Log.d("news",response);

                        // JSON 오브젝트가 아닌 다른 정보가 오면 에러가 나기때문에
                        // try catch 를 활용하여 JSONException 예외처리한다.
                        try {
                            // json 형태로 되어있는 response 를 JSONObject 에 담는다.
                            JSONObject jsonObj = new JSONObject(response);

                            // 원하는 정보가 json array 에 차례대로 들어있기 때문에
                            // JSONObject 안에있는 array 를 이름으로 찾아서 JSONArray 안에 담는다.
                            JSONArray arrayArticles = jsonObj.getJSONArray("articles");

                            // NewsData 에 title, description, imageUrl 을 셋팅하고
                            // 각각의 배열에 담겨있는 뉴스를
                            // List<NewsData> 에 차례대로 담는다.
                            List<NewsData> news = new ArrayList<NewsData>();

                            // for 문으로 위에서 찾은 arrayList 안에있는 내용을 순서대로 검사한다.
                            for(int i = 0, j = arrayArticles.length(); i<j; i++){

                                // array 안에는 원하는 정보인 title, description, imageUrl 등 이
                                // object 안에 담겨있기때문에 다시 JSONObject 안에 담는다.
                               JSONObject obj = arrayArticles.getJSONObject(i);
                                // Log.d("news",obj.toString());
                                // response --> NewsData Class 로 분류

                                // NewsData 객체를 생성하여
                                // 위에서 찾은 object 안에 title, description, imageUrl 정보를
                                // obj.get(name) 으로 찾아서
                                // 각각 newsData.setTitle(name),setDescription(name),setUrlToImage(name)
                                // 메소드를 활용하여 값을 넣어준다.
                                NewsData newsData = new NewsData();
                                newsData.setTitle(obj.getString("title"));
                                newsData.setDescription(obj.getString("description"));
                                newsData.setUrlToImage(obj.getString("urlToImage"));

                                // 모두 추가되면 for 문 밖에서 생성한
                                // List<NewsData> 에 추가한다.
                                news.add(newsData);
                            }


                            // 2. 화면에 값 셋팅하기
                            // specify an adapter (see also next example)
                            // MyAdapter 는 생성시 List<NewsData> 와 context, onClickListener 를 받는다.
                            mAdapter = new MyAdapter(news, MainActivity.this, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // 2-1 onclick 리스너
                                    // MyAdapter 내부에는 title, description , image 를 각각 표시할 수 있는 view 가 있고
                                    // 그 view 는 linearLayout 이 감싸고 있다.
                                    // LinearLayout 을 클릭할 수 있도록 설정하고 클릭 시
                                    // NewsPageActivity.class 로 이동하고 Intent 로 클릭한 뉴스의 정보를 넘겨서 출력하도록 한다.

                                    // news 는 각각의 view 에 구분되어서 리사이클러뷰를 통해 셋 된다.
                                    // 즉, view 가 나뉘어지는 것인데 view 에 셋팅된 각각의 정보에 접근하기 위해서 구분해 주어야 하는데
                                    // setTag(); 를 통해서 구분지을 수 있다.
                                    // setTag 시 onBindViewHolder 의 int position 값을 셋팅한다.
                                    // position 은 각각의 View 를 구분짓는 값이다.
                                    // Tag 는 Object 로 되어있으므로 Object 에 담는다.
                                    Object obj = v.getTag();
                                    // tag 를 담은 object 가 비어있지 않다면
                                    if(obj != null){
                                        // int position 을 생성하고 그안에 v.getTag(); 를  int 로 변환하여 담는다.
                                        int position = (int)obj;
                                        // ((MyAdapter)mAdapter) -> 친자확인으로 안에있는 public 메소드와 변수에 접근할 수 있도록 해준다.
                                        // getNews() 메소드는 포지션 입력시 List<NewsData> 에서
                                        // 해당 포지션 값의 NewsData 객체를 꺼내어 리턴한다.
                                        // ((MyAdapter)mAdapter).getNews(position); // ->즉, 이것은 NewsData 리스트 안에있는 해당 포지션 값에 해당하는 NewsData 객체이다.

                                        // Intent  를 활용하여 NewsPageActivity 로 데이터를 전송하고 이동한다.
                                        Intent intent = new Intent(MainActivity.this, NewsPageActivity.class);
                                        // 1. 본문
                                        // 2. 전체
                                        // 2-1. 하나씩
                                        // 2-2. 한번에 다 넘기기 --> 이방법을 사용해본다.
                                        // putExtra() 에 "news" 라는 이름으로 NewsData 객체를 전송한다.
                                        // putExtra 로 객체를 전달하기 위해선
                                        // 객체에 다음과같이 Serializable 이 implements 되어있어야 한다.
                                        // 되어있지 않다면 컴파일 에러를 발생시킨다.
                                        // public class NewsData implements Serializable { // Serializable -> 직렬화
                                        intent.putExtra("news",((MyAdapter)mAdapter).getNews(position));
                                        startActivity(intent); // 액티비티 이동
                                    }
                                }
                            });

                            // 리사이클러뷰의 setAdapter 에 MyAdapter 를 입력하면
                            // 자동으로 리사이클러뷰에 정보들이 차례대로 기록된다.
                            // 자세히 알아보기 위해 MyAdapter.java 로 이동해본다.
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
        // 큐에 최종적으로 응답받은 내용들을 추가한다.
        queue.add(stringRequest);

    }
}
