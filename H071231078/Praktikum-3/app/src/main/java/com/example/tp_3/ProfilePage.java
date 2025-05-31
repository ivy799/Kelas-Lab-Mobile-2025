package com.example.tp_3;

import static com.example.tp_3.ProfileFeedDataSource.ProfileFeeds;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ProfilePage extends AppCompatActivity {

    ImageView add;
    private static final int ADD_POST = 1;
    ProfileFeedAdapter profileFeedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView rvProfileFeeds = findViewById(R.id.rv_profileFeeds);
        RecyclerView rvHighlightStory = findViewById(R.id.rv_highlightStory);
        rvProfileFeeds.setHasFixedSize(true);
        rvHighlightStory.setHasFixedSize(true);

        profileFeedAdapter = new ProfileFeedAdapter(ProfileFeeds);
        StoryAdapter storyAdapter = new StoryAdapter(StroryDataSource.Stories);
        rvProfileFeeds.setAdapter(profileFeedAdapter);
        rvHighlightStory.setAdapter(storyAdapter);

        add = findViewById(R.id.add);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilePage.this, Post.class);
            startActivityForResult(intent, ADD_POST);
        });

        ImageView homeIcon, AddPostIcon, ProfileIcon;
        homeIcon = findViewById(R.id.homeIcon);
        AddPostIcon = findViewById(R.id.AddPostIcon);
        ProfileIcon = findViewById(R.id.profileIcon);

        homeIcon.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePage.this, MainActivity.class);
            startActivity(intent);
        });

        AddPostIcon.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePage.this, Post.class);
            startActivityForResult(intent, ADD_POST);
        });

        ProfileIcon.setOnClickListener(view -> {
            Intent intent = new Intent(ProfilePage.this, ProfilePage.class);
            startActivity(intent);
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_POST && resultCode == RESULT_OK && data != null) {
            String imagePath = data.getStringExtra("image");
            String feedText = data.getStringExtra("feed");

            ProfileFeed newPost = new ProfileFeed(
                    "HANZZ",
                    feedText,
                    "0", "0", "0",
                    "Today • See translation",
                    imagePath
            );

            ProfileFeeds.add(0, newPost);
            profileFeedAdapter.notifyItemInserted(0);

        }
    }
}