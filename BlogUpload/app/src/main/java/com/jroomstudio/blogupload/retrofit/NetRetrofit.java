package com.jroomstudio.blogupload.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetRetrofit {


    // NetRetrofit 싱글톤
    private static NetRetrofit ourInstance = new NetRetrofit();

    public static NetRetrofit getInstance() { return ourInstance; }

    private NetRetrofit(){ }

    // http 클라이언트 생성
    // OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor()).build();

    // 레트로핏 생성
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://115.68.221.104/")
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
