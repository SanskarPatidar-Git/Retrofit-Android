package com.tutorial.retrofit.api.interceptors;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();
        if(isAuthRequired()){
            String authToken = "";
            request.header("Authorization : Bearer "+authToken);
        }
        return chain.proceed(request);
    }

    private boolean isAuthRequired(){
        return false;
    }
}
