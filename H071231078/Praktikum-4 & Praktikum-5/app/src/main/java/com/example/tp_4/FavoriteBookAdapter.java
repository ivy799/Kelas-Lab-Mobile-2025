package com.example.tp_4;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class FavoriteBookAdapter extends RecyclerView.Adapter<FavoriteBookAdapter.FavoriteBookViewHolder> {

    private ArrayList<Book> Favbooks;

    public FavoriteBookAdapter(ArrayList<Book> favbooks) {
        this.Favbooks = favbooks;
    }


    @NonNull
    @Override
    public FavoriteBookAdapter.FavoriteBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_layout, parent, false);
        return new FavoriteBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteBookAdapter.FavoriteBookViewHolder holder, int position) {
        Book book = Favbooks.get(position);
        holder.setData(book);
    }

    @Override
    public int getItemCount() {
        return Favbooks.size();
    }

    public void updateData(ArrayList<Book> newFavoriteBooks) {
        this.Favbooks = newFavoriteBooks;
        notifyDataSetChanged();
    }

    public class FavoriteBookViewHolder extends RecyclerView.ViewHolder {
        private final ImageView cover;
        private Book book;
        private TextView title;

        public FavoriteBookViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.cover);
            title = itemView.findViewById(R.id.judul);
            cover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), BookDetailActivity.class);
                    intent.putExtra("book", book);
                    itemView.getContext().startActivity(intent);
                }

            });
        }
        public void setData(Book book) {
            this.book = book;
            this.title.setText(book.getJudul());

            if (book.getCoverUri() != null) {
                File imageFile = new File(itemView.getContext().getFilesDir(), book.getCoverUri());
                if (imageFile.exists()) {
                    cover.setImageURI(Uri.fromFile(imageFile));
                } else {
                    cover.setImageResource(R.drawable.atomichabbit);
                }
            } else if (book.getCover() != null) {
                cover.setImageResource(book.getCover());
            } else {
                cover.setImageResource(R.drawable.atomichabbit);
            }
        }

    }
}
