package com.example.tp_4;

import static com.example.tp_4.BookDataSource.FavoriteBooks;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView ivCover = findViewById(R.id.iv_cover);
        TextView tvJudul = findViewById(R.id.tv_judul);
        TextView tvPenulis = findViewById(R.id.tv_penulis);
        TextView tvTahunTerbit = findViewById(R.id.tv_tahunTerbit);
        TextView tvBlurb = findViewById(R.id.tv_blurb);
        TextView tvRating = findViewById(R.id.tv_rating);
        TextView tvGenre = findViewById(R.id.tv_genre);
        TextView tvReview = findViewById(R.id.tv_review);
        Button likeButton = findViewById(R.id.likeButton);



        Book book = getIntent().getParcelableExtra("book");

        if (book.getCoverUri() != null) {
            File imageFile = new File(getFilesDir(), book.getCoverUri());
            System.out.println(imageFile);
            ivCover.setImageURI(Uri.fromFile(imageFile));
        } else if (book.getCover() != null) {
            ivCover.setImageResource(book.getCover());
        } else {
            ivCover.setImageResource(R.drawable.atomichabbit);
        }
        tvJudul.setText(book.getJudul());
        tvPenulis.setText(book.getPenulis());
        tvTahunTerbit.setText(" • " + book.getTahunTerbit());
        tvBlurb.setText(book.getBlurb());

        tvRating.setText("Rating: " + book.getRating().toString());
        if (book.getRating() == null){
            tvRating.setText("Rating: 0");
            tvRating.setTextColor(getResources().getColor(R.color.black, null));
            tvRating.setTypeface(null, android.graphics.Typeface.ITALIC);
        } else {
            tvRating.setText("Rating: " + book.getRating().toString());
            tvRating.setTextColor(getResources().getColor(R.color.black, null));
            tvRating.setTypeface(null, android.graphics.Typeface.NORMAL);
        }

        tvGenre.setText("Genre: " + TextUtils.join(", ", book.getGenre()));


        if (book.getReview() == null || book.getReview().isEmpty()) {
            tvReview.setText("No reviews yet. Be the first to review this book!");
            tvReview.setTextColor(getResources().getColor(R.color.black, null));
            tvReview.setTypeface(null, android.graphics.Typeface.ITALIC);
        } else {
            StringBuilder reviewText = new StringBuilder();
            for (String comment : book.getReview()) {
                reviewText.append("★ ").append(comment).append("\n");
            }
            tvReview.setText(reviewText.toString().trim());
            tvReview.setTextColor(getResources().getColor(R.color.black, null));
            tvReview.setTypeface(null, android.graphics.Typeface.NORMAL);
        }


        likeButton.setOnClickListener(view -> {
            if (book.getFavorite()) {
                book.setFavorite(false);
                FavoriteBooks.remove(book);
            } else {
                book.setFavorite(true);
                FavoriteBooks.add(book);
            }
        });




    }
}