package com.example.tp_3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFeedDetails extends AppCompatActivity {

    ImageView image;
    CircleImageView iv_profile;
    TextView likeCount, shareCount, commentCount, feed, time, nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_feed_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        image = findViewById(R.id.image);
        likeCount = findViewById(R.id.likeCount);
        shareCount = findViewById(R.id.shareCount);
        commentCount = findViewById(R.id.CommentCount);
        feed = findViewById(R.id.feed);
        time = findViewById(R.id.time);
        image = findViewById(R.id.image);
        nickname = findViewById(R.id.nickname);
        iv_profile = findViewById(R.id.iv_profile);

        String fileName = getIntent().getStringExtra("image");

        if (fileName != null) {
            File imageFile = new File(getFilesDir(), fileName);
            if (imageFile.exists()) {
                image.setImageURI(Uri.fromFile(imageFile));
            } else {
                image.setImageResource(R.drawable.image1);
            }
        } else {
            int resId = getIntent().getIntExtra("image", R.drawable.image1);
            image.setImageResource(resId);
        }
        likeCount.setText(getIntent().getStringExtra("likeCount"));
        shareCount.setText(getIntent().getStringExtra("shareCount"));
        commentCount.setText(getIntent().getStringExtra("commentCount"));
        feed.setText(getIntent().getStringExtra("feed"));
        time.setText(getIntent().getStringExtra("time"));
        String name = getIntent().getStringExtra("name");
        nickname.setText(name != null ? name : "HANZZ");
        iv_profile.setImageResource(getIntent().getIntExtra("profile", R.drawable.image3));


        ImageView homeIcon, AddPostIcon, ProfileIcon, back;
        homeIcon = findViewById(R.id.homeIcon);
        AddPostIcon = findViewById(R.id.AddPostIcon);
        ProfileIcon = findViewById(R.id.profileIcon);
        back = findViewById(R.id.back);

        homeIcon.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileFeedDetails.this, MainActivity.class);
            startActivity(intent);
        });

        AddPostIcon.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileFeedDetails.this, Post.class);
            startActivity(intent);
        });

        ProfileIcon.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileFeedDetails.this, ProfilePage.class);
            startActivity(intent);
        });

        TextView backLabel = findViewById(R.id.backLabel);

        if (name == null) {
            back.setOnClickListener(view -> {
                Intent intent = new Intent(ProfileFeedDetails.this, ProfilePage.class);
                startActivity(intent);
            });
        } else {
            back.setVisibility(View.GONE);
            backLabel.setVisibility(View.GONE);
        }

    }

    private Uri copyUriToInternalStorage(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            if (inputStream == null) return null;

            File file = new File(getFilesDir(), "tempImage.jpg");
            OutputStream outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.close();
            inputStream.close();

            return Uri.fromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}