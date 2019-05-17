package com.example.projectairline.Utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BaseURL = "https://logisparktech.com/airlineSchedule/api/";



    private static RetrofitClient mInstance;
    private Retrofit retrofit;


    private RetrofitClient(){

        retrofit = new Retrofit.Builder().baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized RetrofitClient getmInstance(){
        if (mInstance == null){
            mInstance = new RetrofitClient();
        }

        return mInstance;
    }

    public API getApi(){
        return retrofit.create(API.class);

    }


}
