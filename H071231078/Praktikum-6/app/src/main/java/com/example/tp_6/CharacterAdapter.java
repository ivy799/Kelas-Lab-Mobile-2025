package com.example.tp_6;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOAD_MORE = 1;
    private boolean showLoadMore = true;

    public interface OnLoadMoreClickListener {
        void onLoadMoreClicked();
    }

    private OnLoadMoreClickListener onLoadMoreClickListener;

    public void setOnLoadMoreClickListener(OnLoadMoreClickListener listener) {
        this.onLoadMoreClickListener = listener;
    }


    @Override
    public int getItemViewType(int position) {
        if (position < characterList.size()) {
            return TYPE_ITEM;
        } else {
            return TYPE_LOAD_MORE;
        }
    }


    public List<Character> characterList;

    public CharacterAdapter(List<Character> characterList) {
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_card, parent, false);
            return new CharacterViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.button, parent, false);
            return new LoadMoreViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CharacterViewHolder) {
            Character character = characterList.get(position);
            ((CharacterViewHolder) holder).bind(character);
        }
    }

    @Override
    public int getItemCount() {
        return characterList.size() + 1;
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nameTextView;
        private TextView speciesTextView;

        private Character character;

        public CharacterViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_profile);
            nameTextView = itemView.findViewById(R.id.tv_name);
            speciesTextView = itemView.findViewById(R.id.tv_species);
        }

        public void bind(Character character) {
            this.character = character;
            Picasso.get().load(character.getImage()).into(imageView);
            nameTextView.setText(character.getName());
            speciesTextView.setText(character.getSpecies());

            imageView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), CharacterDetailActivity.class);
                intent.putExtra("id", String.valueOf(character.getId()));
                itemView.getContext().startActivity(intent);
            });
        }

    }

    public class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        Button buttonLoadMore;
        public LoadMoreViewHolder(View itemView) {
            super(itemView);
            buttonLoadMore = itemView.findViewById(R.id.loadMoreButton);
            buttonLoadMore.setOnClickListener(v -> {
                if (onLoadMoreClickListener != null) {
                    onLoadMoreClickListener.onLoadMoreClicked();
                }
            });
        }
    }



}
