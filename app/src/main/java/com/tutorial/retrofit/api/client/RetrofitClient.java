package com.tutorial.retrofit.api.client;

import com.tutorial.retrofit.api.interceptors.AuthInterceptor;
import com.tutorial.retrofit.api.interceptors.LogInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getHttpClient())
                    .addConverterFactory(GsonConverterFactory.create()) //Serialization of JSON
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor())  // Log interceptor
                .addInterceptor(new AuthInterceptor())  // Auth interceptor

                /* Optional
                Time outs for network calling. Default values for all is 10.
                Change the configs according to your requirements.
                If connection , write and read time exceeds a Timeout Exception is thrown. SocketTimeoutException
                You can catch it in onFailure callback.
                */
                .connectTimeout(15, TimeUnit.SECONDS) // Connection establish with server
                .writeTimeout(15,TimeUnit.SECONDS) // Write data to server
                .readTimeout(15,TimeUnit.SECONDS) // Read data from server

                .build();
    }

    /*
    Predefined library for http logging.
    If You need custom logging use LogInterceptor class.
     */
    private static Interceptor getLoggingInterceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }
}

/*
Interceptors are used for logging , modifying the request , adding headers etc.
Retrofit uses OkHttp Client for API calling and managing most of the things by itself.
 */
