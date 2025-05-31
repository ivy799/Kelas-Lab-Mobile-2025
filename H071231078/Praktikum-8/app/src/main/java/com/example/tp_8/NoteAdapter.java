package com.example.tp_8;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private final ArrayList<Note> listNotes = new ArrayList<>();
    private final Activity activity;

    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListNotes(ArrayList<Note> notes){
        listNotes.clear();
        if (notes.size() > 0){
            this.listNotes.addAll(notes);
        }
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {
        holder.bind(listNotes.get(position));
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        final TextView tvTitle, tvDesc, tvDate;
        final CardView cardView;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tv_title);
            this.tvDesc = itemView.findViewById(R.id.tv_description);
            this.tvDate = itemView.findViewById(R.id.tv_date);
            this.cardView = itemView.findViewById(R.id.card_view);
        }

        void bind(Note note){
            tvTitle.setText(note.getTitle());
            tvDesc.setText(note.getDescription());
            tvDate.setText(note.getTime());
            cardView.setOnClickListener(View -> {
                Intent intent = new Intent(activity, FormActivity.class);
                intent.putExtra(FormActivity.EXTRA_NOTE, note);
                activity.startActivityForResult(intent, FormActivity.REQUEST_UPDATE);
            });
        }
    }
}
