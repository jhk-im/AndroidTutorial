package com.jroomstudio.retrofit_example.temp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * ApiService 인터페이스
 * -> 통신 할 API 의 HTTP Method 를 정의한다.
 **/

public interface ApiService {

    /**
     * Header 에 key Authorization String 형태의 토큰을 요구함
     **/
    @POST("login")
    Call<Authorization> loginAccount(@Header("Authorization") String authKey);

    @GET("accounts/{accountId}")
    Call<Authorization> getAccountInfo(@Header("Authorization") String authKey,
                                       @Path("accountId") String accountId);

    @GET("accounts/test")
    Call<Authorization> getTestApi(@Header("Authorization") String authKey,
                                   @Query("params") String accountId);

    @FormUrlEncoded
    @POST("user/login")
    Call<Result> getInfo(@Field("params") String name);
}
