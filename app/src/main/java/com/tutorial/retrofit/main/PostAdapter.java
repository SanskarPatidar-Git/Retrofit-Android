package com.tutorial.retrofit.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tutorial.retrofit.Post;
import com.tutorial.retrofit.databinding.ItemPostsBinding;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> postList;

    public PostAdapter(List<Post> postList){
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostsBinding binding = ItemPostsBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(holder.getAdapterPosition());
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        private ItemPostsBinding binding;

        public PostViewHolder(@NonNull ItemPostsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Post post){
            binding.setPost(post);
            binding.executePendingBindings();
        }
    }
}
