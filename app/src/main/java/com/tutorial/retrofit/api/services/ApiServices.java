package com.tutorial.retrofit.api.services;

import com.tutorial.retrofit.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("posts")
    Call<List<Post>> getPosts();
}
