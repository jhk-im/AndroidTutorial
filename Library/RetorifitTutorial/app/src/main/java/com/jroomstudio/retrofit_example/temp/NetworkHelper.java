package com.jroomstudio.retrofit_example.temp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {

    private Retrofit retrofit;
    private ApiService apiService;
    public static NetworkHelper networkHelper = new NetworkHelper();

    public NetworkHelper() {
        /*
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASEURL) //api의 baseURL
                .addConverterFactory(GsonConverterFactory.create()) // 나는 데이터를 자동으로 컨버팅할 수 있게 GsonFactory를 씀
                .build();
        */

        apiService =retrofit.create(ApiService.class); //실제 api Method들이선언된 Interface객체 선언
    }

    public static NetworkHelper getInstance() { //싱글톤으로 선언된 레트로핏 객체 얻는 용
        return networkHelper;
    }


    public ApiService getApiService() { // API Interface 객체 얻는 용
        return apiService;
    }

}
