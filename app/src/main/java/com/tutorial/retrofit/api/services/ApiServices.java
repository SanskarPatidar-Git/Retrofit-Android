package com.tutorial.retrofit.api.services;

import com.tutorial.retrofit.main.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/*
Api services - Requests
 */
public interface ApiServices {

    /*
    Method - GET
    Endpoint - /posts
    Request Body - None
    Response - List of type Post
     */
    @GET("posts")
    Call<List<Post>> getPosts();

    /* ===================================== SOME EXAMPLES ============================ */

    /*
    Example 1 - (Post method with request body)

    @POST("comments")
    Call<String> post(@Body Post post);
     */



    /*
    Example 2 - (Passing headers and path variable)
    You can pass header here in request param of method also but it need to write in each method instead we can use
    interceptor and add headers like we use Auth header in AuthInterceptor.

    @POST("comments/{id}")
    Call<String> post(@Header("Authorization") String authHeader , @Path("id") int id);
     */
}
