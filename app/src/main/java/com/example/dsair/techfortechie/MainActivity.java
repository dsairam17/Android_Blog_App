package com.example.dsair.techfortechie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Post> mPostList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPostList = new ArrayList<Post>();

        mPostList.add(new Post(1, "AMD Threadripper vs Intel Core-i9", "Temp Content", "amd-threadripper", R.drawable.placeholder_600x400, "sairam"));
        mPostList.add(new Post(2, "Second Post", "Temp", "second-post", R.drawable.placeholder_600x400, "sairam"));

        PostAdapter adapter = new PostAdapter(this, mPostList);

        recyclerView.setAdapter(adapter);

    }


}
