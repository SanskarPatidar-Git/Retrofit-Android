package com.tutorial.retrofit.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.tutorial.retrofit.R;
import com.tutorial.retrofit.api.client.RetrofitClient;
import com.tutorial.retrofit.api.services.ApiServices;

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

    /*
    Simple code for getting some data from server using Retrofit.
    here we are using dummy url to get data.
     */
    private void getPostsFromApi(){
        Toast.makeText(this, "Fetching posts from Server", Toast.LENGTH_SHORT).show();

        Retrofit retrofit = RetrofitClient.getClient();  //get the retrofit instance
        ApiServices services = retrofit.create(ApiServices.class);

        /*
        enqueue() is used to call the request on background thread.
        once the response is fetched rest of code in this callbacks methods are executed on main thread.
         */
        services.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                System.out.println("getPostsFromApi::onResponse() Thread->"+Thread.currentThread().getName());

                //If response is successful
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

    /*
    If you are using execute instead of enqueue you need to call the request on background thread.
    until the response is not fetched till that new thread will be blocked.
    It is recommended to use enqueue as Retrofit itself manage the thread for requests.
     */
}