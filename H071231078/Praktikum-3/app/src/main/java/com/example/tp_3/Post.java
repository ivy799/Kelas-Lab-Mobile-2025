package com.example.tp_3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.io.FileOutputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class Post extends AppCompatActivity {

    ImageView image;
    EditText feed;
    MaterialButton post;

    private static final int PICK_IMAGE_REQUEST = 1;

    private String currentImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        image = findViewById(R.id.iv_profile);
        feed = findViewById(R.id.et_feed);
        post = findViewById(R.id.post);

        image.setOnClickListener(v -> {
            openImageChooser();
        });

        post.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("image", currentImagePath);
            resultIntent.putExtra("feed", feed.getText().toString());
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        ImageView homeIcon, AddPostIcon, ProfileIcon;
        homeIcon = findViewById(R.id.homeIcon);
        AddPostIcon = findViewById(R.id.AddPostIcon);
        ProfileIcon = findViewById(R.id.profileIcon);

        homeIcon.setOnClickListener(view -> {
            Intent intent = new Intent(Post.this, MainActivity.class);
            startActivity(intent);
        });

        AddPostIcon.setOnClickListener(view -> {
            Intent intent = new Intent(Post.this, Post.class);
            startActivity(intent);
        });

        ProfileIcon.setOnClickListener(view -> {
            Intent intent = new Intent(Post.this, ProfilePage.class);
            startActivity(intent);
        });

    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            handleSelectedImage(data.getData());
        }
    }

    private void handleSelectedImage(Uri imageUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            image.setImageBitmap(bitmap);
            currentImagePath = saveImageToStorage(bitmap);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String saveImageToStorage(Bitmap bitmap) {
        String fileName = "profile_" + System.currentTimeMillis() + ".jpg";
        try (FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}