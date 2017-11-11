package com.ptp.phamtanphat.quanlyhocsinh1409.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KhoaPhamPC on 11/11/2017.
 */

public class APIRetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String url){
        //Dinh nghia ro cho class cua gson de covert cac doi tuong ben inteface gui nhan du lieu
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient okhttp = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .readTimeout(10000,TimeUnit.MILLISECONDS)
                .build();
         retrofit = new Retrofit.Builder()
                 .client(okhttp)
                 .baseUrl(url)
                 .addConverterFactory(GsonConverterFactory.create(gson))
                 .build();
        return retrofit;
    }
}
