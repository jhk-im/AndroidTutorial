package com.jroomstudio.retrofit_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText.getText().toString();

                if (!id.isEmpty()) {
                    /*
                    * NetRetrofit. -->  NetRetrofit 싱글톤 생성
                    * getInstance(). --> 생성된 instance 가져오기
                    * getService(). --> 연결된 Service Instance 가져오기
                    * getListRepos(id). --> Repository 리스트를 가져오는 API
                    *                   --> GET Request 사용
                    *                   --> 응답형식 jsonObject 를 담는 ArrayList
                    */
                    Call<ArrayList<JsonObject>> res =
                            NetRetrofit.getInstance().getService().getListRepos(id);
                    res.enqueue(new Callback<ArrayList<JsonObject>>() {
                        @Override
                        public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                            // 통신이 정상적으로 되었다면 onResponse
                            Log.d("Retrofit", response.toString());
                            if (response.body() != null)
                                textView.setText(response.body().toString());
                        }

                        @Override
                        public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                            // 통신중 에러가 발생했다면 onFailure
                            Log.e("Err", t.getMessage());
                        }
                    });
                } else{
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
