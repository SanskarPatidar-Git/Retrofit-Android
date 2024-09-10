package com.tutorial.retrofit;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.tutorial.retrofit.api.client.RetrofitClient;
import com.tutorial.retrofit.api.services.ApiServices;
import com.tutorial.retrofit.main.PostAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPostsFromApi();
    }

    private void getPostsFromApi(){
        Retrofit retrofit = RetrofitClient.getClient();
        ApiServices services = retrofit.create(ApiServices.class);

        services.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                System.out.println("getPostsFromApi::onResponse() Thread->"+Thread.currentThread().getName());

                if(response.isSuccessful()){
                    List<Post> postList = response.body();
                    setPostAdapter(postList);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                System.out.println("getPostsFromApi::onFailure() -> "+t.getMessage());
            }
        });
    }

    private void setPostAdapter(List<Post> postList){
        PostAdapter adapter = new PostAdapter(postList);
        RecyclerView rcv = findViewById(R.id.recyclerView);
        rcv.setAdapter(adapter);
    }
}