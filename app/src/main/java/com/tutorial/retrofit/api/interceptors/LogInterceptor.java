package com.tutorial.retrofit.api.interceptors;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
Custom logging interceptor to log network requests and responses.
 */
public class LogInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        //Log Network Request
        Request request = chain.request();
        System.out.println("-------------------- API Request ----------------- \n URL : "+request.url()
                +"\n Method : "+request.method()
                +"\n Body : "+request.body()
                +"\n ------------------ End of Request -------------");

        //Log Network Response
        /* Response response = chain.proceed(request);
        System.out.println("-------------------- API Response ----------------- \n Message : "+response.message()
                +"\n Body : "+response.body().string()
                +"\n ------------------ End of Response -------------");
         */

        return chain.proceed(request);
    }
}
