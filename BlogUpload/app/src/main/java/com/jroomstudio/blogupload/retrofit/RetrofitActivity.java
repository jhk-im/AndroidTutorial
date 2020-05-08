package com.jroomstudio.blogupload.retrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jroomstudio.blogupload.R;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    Button signIn,logIn,logOut;
    TextView dataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        signIn = (Button) findViewById(R.id.sign_in_button);
        signIn.setOnClickListener(v -> {

            //retrofitPATCH();
            //retrofitPUT();
            //retrofitJsonPOST();
            //retrofitHashMapPOST();
            //retrofitFieldPOST();
            //retrofitGET();

        });
        logIn = (Button) findViewById(R.id.login_button);
        logOut = (Button) findViewById(R.id.logout_button);
        dataTextView = (TextView) findViewById(R.id.data_text_view);

    }

    //GET 전송
    void retrofitGET(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        retrofitService.getData("wante0301@naver.com","123123").
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.e("post", response.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("onFailure",t.getMessage());
                    }
                });
    }

    // POST JSON 전송
    void retrofitJsonPOST(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        Member member = new Member(
                "wanted0301@naver.com",
                "kim",
                "img",
                "123123",
                true,
                true,
                0,
                true
                );

        retrofitService.postData("application/json",member).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.e("post", response.toString());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("onFailure",t.getMessage());
            }
        });


    }

    // POST HashMap 전송
    void retrofitHashMapPOST(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        HashMap<String, Object> input = new HashMap<>();
        input.put("member_email", "kkk@kkk.com");
        input.put("member_name","kkk");
        input.put("auto_password", "123123");
        input.put("photo_url", "no");
        input.put("dark_theme", true);
        input.put("push_notice", true);
        input.put("login_type", 0);
        input.put("login_status", true);

        retrofitService.postData(input).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.e("post", response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailure",t.getMessage());
            }
        });
    }

    // POST Field 전송
    void retrofitFieldPOST(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        retrofitService.postData(
                "k@nate.com",
                "kim",
                "123123",
                "no",
                true,
                true,
                1,
                true
                ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.e("post", response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailure",t.getMessage());
            }
        });

    }

    // PUT 데이터 전송
    void retrofitPUT(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        Member member = new Member(
                "wanted0301@naver.com",
                "kim",
                "img",
                "123123",
                true,
                true,
                0,
                true
        );

        retrofitService.putData(member).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.e("post", response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailure",t.getMessage());
            }
        });
    }

    // PATCH 데이터 전송
    void retrofitPATCH(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        retrofitService.patchData("wante@naver.com","123123").
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.e("post", response.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("onFailure",t.getMessage());
                    }
                });
    }
}
