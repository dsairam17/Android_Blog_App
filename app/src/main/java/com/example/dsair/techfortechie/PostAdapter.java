package com.example.dsair.techfortechie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context mContext;
    private List<Post> mPostList;
    public PostAdapter(Context context, List<Post> postList) {
        this.mContext = context;
        this.mPostList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.post_item, null);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = mPostList.get(position);
        holder.setListeners();
        holder.setData(post, position);

    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mTitleTextView;
        ImageView mPostImageView;
        int mPosition;
        Post mCurrentObject;

        public PostViewHolder(View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.title_textview);
            mPostImageView = itemView.findViewById(R.id.post_image);
        }

        public void setListeners(){
            mTitleTextView.setOnClickListener(PostViewHolder.this);
            mPostImageView.setOnClickListener(PostViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.post_image:
                    openPost(mPosition);
                    break;
                case R.id.title_textview:
                    openPost(mPosition);
                    break;
            }
        }

        public void setData(Post post, int position) {
            mTitleTextView.setText(post.getTitle());
//            mPostImageView.setImageDrawable(mContext.getResources().getDrawable(post.getImageLoc()));
            new ImageLoadTask(post.getImageLoc(), mPostImageView).execute();
            this.mPosition = position;
        }

        private void openPost(int position) {
            Post post = mPostList.get(position);
            Toast toast = Toast.makeText(mContext, post.getPermalink(), Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(mContext, WebPostActivity.class);
            intent.putExtra("permalink", post.getPermalink());
            mContext.startActivity(intent);
        }

    }
}
