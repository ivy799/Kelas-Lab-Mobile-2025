package com.example.tp_3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileDetails extends AppCompatActivity {

    CircleImageView iv_profile;
    ImageView image;

    TextView username, feed, hour, postCount, followersCount, followingCount, likeCount, commentCount, shareCount, backLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Random random = new Random();



        iv_profile = findViewById(R.id.iv_profile);
        username = findViewById(R.id.nickname);
        postCount = findViewById(R.id.postCount);
        followersCount = findViewById(R.id.followersCount);
        followingCount = findViewById(R.id.followingCount);
        backLabel = findViewById(R.id.backLabel);
        image = findViewById(R.id.feedImage);

        iv_profile.setImageResource(getIntent().getIntExtra("profile", R.drawable.image1));
        username.setText(getIntent().getStringExtra("username"));
        postCount.setText(String.valueOf(random.nextInt(1000)));
        followersCount.setText(String.valueOf(random.nextInt(1000)));
        followingCount.setText(String.valueOf(random.nextInt(1000)));
        backLabel.setText(getIntent().getStringExtra("username"));
        image.setImageResource(getIntent().getIntExtra("image", R.drawable.image1));


        String feed = getIntent().getStringExtra("feed");
        String hour = getIntent().getStringExtra("hour");
        String postCount = getIntent().getStringExtra("postCount");
        String followersCount = getIntent().getStringExtra("followersCount");
        String followingCount = getIntent().getStringExtra("followingCount");




        ImageView homeIcon, AddPostIcon, ProfileIcon, back;
        homeIcon = findViewById(R.id.homeIcon);
        AddPostIcon = findViewById(R.id.AddPostIcon);
        ProfileIcon = findViewById(R.id.profileIcon);
        back = findViewById(R.id.back);

        homeIcon.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileDetails.this, MainActivity.class);
            startActivity(intent);
        });

        AddPostIcon.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileDetails.this, Post.class);
            startActivity(intent);
        });

        ProfileIcon.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileDetails.this, ProfilePage.class);
            startActivity(intent);
        });

        back.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileDetails.this, MainActivity.class);
            startActivity(intent);
        });

        image.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileDetails.this, ProfileFeedDetails.class);
            intent.putExtra("name", getIntent().getStringExtra("username"));
            intent.putExtra("profile", getIntent().getIntExtra("profile", R.drawable.image1));
            intent.putExtra("image", getIntent().getIntExtra("image", R.drawable.image1));
            intent.putExtra("likeCount", getIntent().getStringExtra("likeCount"));
            intent.putExtra("shareCount", getIntent().getStringExtra("shareCount"));
            intent.putExtra("commentCount", getIntent().getStringExtra("commentCount"));
            intent.putExtra("feed", getIntent().getStringExtra("feed"));
            intent.putExtra("time", getIntent().getStringExtra("hour"));
            startActivity(intent);
        });

    }
}