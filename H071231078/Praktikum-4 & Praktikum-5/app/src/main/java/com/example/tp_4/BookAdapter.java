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

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private final ArrayList<Book> books;

    public BookAdapter(ArrayList<Book> books) {
        this.books = books;
    }


    @NonNull
    @Override
    public BookAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_layout, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.setData(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private final ImageView cover;
        private final TextView judul;
        private Book book;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.cover);
            judul = itemView.findViewById(R.id.judul);

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
            judul.setText(book.getJudul());
        }
    }
}
