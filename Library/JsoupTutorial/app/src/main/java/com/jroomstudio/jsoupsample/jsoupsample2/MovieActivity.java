package com.jroomstudio.jsoupsample.jsoupsample2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.jroomstudio.jsoupsample.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    private RecyclerView rvMovie;
    private ArrayList<ItemObject> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        rvMovie = (RecyclerView) findViewById(R.id.rv_movie);

        new Description().execute();
    }

    private class Description extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Document doc = Jsoup.
                        //connect("https://sports.news.naver.com/news.nhn?oid=477&aid=0000238277").get();
                        connect("https://movie.naver.com/movie/running/current.nhn").get();
                Log.e("doc","doc : "+ doc);

                Elements mElementDataSize = doc.
                        select("ul[class=lst_detail_t1]").select("li");
                //Log.e("mElementDataSize","mElementsDataSize : "+ mElementDataSize.size());
                //int mElementSize = mElementDataSize.size();

                for(Element elem : mElementDataSize){
                    String title = elem.select("li dt[class=tit] a").text();
                    String link = elem.
                            select("li div[class=thumb] a").attr("href");
                    String imgUrl = elem.
                            select("li div[class=thumb] a img").attr("src");
                    Element rElem = elem.select("dl[class=info_txt1] dt").next().first();
                    String release = rElem.select("dd").text();
                    Element dElem = elem.select("dt[class=tit_t2]").next().first();
                    String director = "감독: " + dElem.select("a").text();
                    list.add(new ItemObject(title,imgUrl,link,release,director));
                }

                Log.e("debug :","List " + mElementDataSize);
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            MovieAdapter movieAdapter = new MovieAdapter(list);
            RecyclerView.LayoutManager layoutManager =
                    new LinearLayoutManager(getApplicationContext());
            rvMovie.setLayoutManager(layoutManager);
            rvMovie.setAdapter(movieAdapter);
        }
    }

}
