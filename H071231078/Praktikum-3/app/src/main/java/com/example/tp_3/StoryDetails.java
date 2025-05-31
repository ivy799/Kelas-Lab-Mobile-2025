package com.example.tp_3;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryDetails extends AppCompatActivity {

    CircleImageView iv_profile;
    TextView username;
    TextView time;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_story_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        iv_profile = findViewById(R.id.iv_profile);
        username = findViewById(R.id.username);
        time = findViewById(R.id.time);
        image = findViewById(R.id.image);

        int resId = getIntent().getIntExtra("story", R.drawable.image1);
        iv_profile.setImageResource(resId);
        username.setText(getIntent().getStringExtra("title"));
        time.setText(getIntent().getStringExtra("time"));
        image.setImageResource(resId);


    }



}