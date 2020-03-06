package com.jroomstudio.retrofit_example;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * NetRetrofit.class
 * - Retrofit 바디
 * - Service Interface 를 이용하여 Retrofit 객체를 생성하는 클래스
 *  - NetRetrofit 싱글톤
 *  - baseUrl 지정
 *  - 파싱을 위한 컨버터 지정
 **/

public class NetRetrofit {

    // NetRetrofit 싱글톤
    private static NetRetrofit ourInstance = new NetRetrofit();

    public static NetRetrofit getInstance() { return ourInstance; }

    private NetRetrofit(){ }

    // http 클라이언트 생성
    // OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor()).build();

    // 레트로핏 생성
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            //.client(client) --> 클라이언트 추가
            .build();

    RetrofitService service = retrofit.create(RetrofitService.class);

    public RetrofitService getService() {
        return service;
    }



/*
 private HttpLoggingInterceptor httpLoggingInterceptor(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("MyGitHubData :", message + "");
            }
        });

        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }
    */
}
