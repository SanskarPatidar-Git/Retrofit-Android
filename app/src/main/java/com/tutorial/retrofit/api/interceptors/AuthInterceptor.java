package com.tutorial.retrofit.api.interceptors;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/*
This interceptor is used to add authorization header or Bearer token.
Whenever Retrofit is used to call an API this interceptors calls.
 */
public class AuthInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();

        /* Check either request need token.*/
        if(isAuthRequired()){
            String authToken = "";  //get the token for local(Shared pref)
            request.header("Authorization : Bearer "+authToken);
        }
        return chain.proceed(request);
    }

    /* Write logic to check whether a API needs Authorization */
    private boolean isAuthRequired(){
        return false;
    }
}
