package com.example.tp_3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView rvMainFeed = findViewById(R.id.rv_feeds);
        rvMainFeed.setHasFixedSize(true);
        FeedAdapter feedsAdapter = new FeedAdapter(MainFeedDataSource.MainFeeds);
        rvMainFeed.setAdapter(feedsAdapter);


        ImageView homeIcon, AddPostIcon, ProfileIcon;
        homeIcon = findViewById(R.id.homeIcon);
        AddPostIcon = findViewById(R.id.AddPostIcon);
        ProfileIcon = findViewById(R.id.profileIcon);

        homeIcon.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        });

        AddPostIcon.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Post.class);
            startActivity(intent);
        });

        ProfileIcon.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ProfilePage.class);
            startActivity(intent);
        });



    }
}