package com.jroomstudio.blogupload.retrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        sharedPreferences = getSharedPreferences("jwt", MODE_PRIVATE);
        Log.e("jwt_token",sharedPreferences.getString("token",""));
        signIn = (Button) findViewById(R.id.sign_in_button);
        signIn.setOnClickListener(v -> {

            // jwt
            //retrofitJWTGetToken();
            //retrofitJWTAuth();

            // 전송 후 데이터 콜백
            //retrofitPOSTJSONCallback();
            //retrofitPOSTHashMapCallback();
            //retrofitGETCallback();

            // 전송만
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

    // 레트로핏 생성
    private RetrofitService createRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        return retrofitService;
    }
    /**
     * 데이터 전송만
     **/
    //GET 전송
    void retrofitGET(){

        RetrofitService retrofitService = createRetrofit();

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

        RetrofitService retrofitService = createRetrofit();

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
                    Log.e("response", response.toString());
                    Log.e("body", response.raw().body()+"");
                    Log.e("header", response.headers()+"");
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
        RetrofitService retrofitService = createRetrofit();

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
        RetrofitService retrofitService = createRetrofit();

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
        RetrofitService retrofitService = createRetrofit();

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
        RetrofitService retrofitService = createRetrofit();
        retrofitService.patchData("wante@naver.com","123123").
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.e("post", response.raw().body() +"");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("onFailure",t.getMessage());
                    }
                });
    }

    /**
     * 데이터 전송 후 데이터 콜백
     **/
    // GET 콜백
    void retrofitGETCallback(){
        RetrofitService retrofitService = createRetrofit();
        retrofitService.getDataCallback("kkk@kkk.com","kkkkkk")
                .enqueue(new Callback<Member>() {
                    @Override
                    public void onResponse(Call<Member> call, Response<Member> response) {
                        Member member = response.body();
                        if (response.isSuccessful()) {
                            //Log.e("member",member.toString());
                            Log.e("post", response.body()+"");
                            Log.e("body", response.raw().body()+"");
                            Log.e("header", response.headers()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<Member> call, Throwable t) {
                        Log.e("onFailure",t.getMessage());
                    }
                });
    }

    // HashMap 콜백
    void retrofitPOSTHashMapCallback(){
        RetrofitService retrofitService = createRetrofit();

        HashMap<String, Object> input = new HashMap<>();
        input.put("member_email", "kkk@kkk.com");
        input.put("member_name","kkk");
        input.put("auto_password", "123123");
        input.put("photo_url", "no");
        input.put("dark_theme", true);
        input.put("push_notice", true);
        input.put("login_type", 0);
        input.put("login_status", true);

        retrofitService.postDataCallback(input).
                enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                // Member member = response.body();
                if (response.isSuccessful()) {
                    Log.e("post", response.body()+"");
                    Log.e("body", response.raw().body()+"");
                    Log.e("header", response.headers()+"");
                }
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.e("onFailure",t.getMessage());
            }
        });
    }

    // json 콜백
    void retrofitPOSTJSONCallback(){

        RetrofitService retrofitService = createRetrofit();
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

        retrofitService.postDataCallback("application/json",member).
                enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                // Member member = response.body();
                if (response.isSuccessful()) {
                    Log.e("post", response.body() + "");
                    Log.e("body", response.raw().body()+"");
                    Log.e("header", response.headers()+"");
                }
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.e("onFailure",t.getMessage());
            }
        });
    }

    /**
     * jwt 구현
     **/
    // 토큰 받기
    void retrofitJWTGetToken(){
        RetrofitService retrofitService = createRetrofit();

        HashMap<String, Object> input = new HashMap<>();
        input.put("member_email", "kkk@kkk.com");
        input.put("member_name","kkk");
        input.put("auto_password", "123123");
        input.put("photo_url", "no");
        input.put("dark_theme", true);
        input.put("push_notice", true);
        input.put("login_type", 0);
        input.put("login_status", true);

        retrofitService.postJWTGetToken(input).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.e("response", response.toString());
                    Log.e("body", response.raw().body()+"");
                    Log.e("header", response.headers()+"");
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token",response.headers().get("Authorization"));
                    editor.apply();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("onFailure",t.getMessage());
            }
        });
    }

    // 토큰 인증
    void retrofitJWTAuth(){
        RetrofitService retrofitService = createRetrofit();
        retrofitService.postJWTAuth(sharedPreferences.getString("token",""))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.e("response", response.toString());
                        Log.e("body", response.raw().body()+"");
                        Log.e("header", response.headers()+"");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("onFailure",t.getMessage());
                    }
                });
    }

}
