package com.jroomstudio.jsoupsample.jsoupsample1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jroomstudio.jsoupsample.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener((view) -> { getWebsite(); });

    }

    private void getWebsite() {
        new Thread ((Runnable) () -> {

            final StringBuffer builder = new StringBuffer();
            try{
                Document doc = Jsoup.connect("https://www.naver.com").get();
                String title = doc.title();
                Elements links = doc.select("a[href]");
                builder.append(title).append("\n");
            }catch (IOException e){
                builder.append("Error");
            }

            runOnUiThread(()->{
                textView.setText(builder.toString());
            });
        }).start();
    }

}
