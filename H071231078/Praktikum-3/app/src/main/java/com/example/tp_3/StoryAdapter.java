package com.example.tp_3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder>{

    private final ArrayList<Story> stories;

    public StoryAdapter(ArrayList<Story> stories) {
        this.stories = stories;
    }

    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.highlight_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.setData(story);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView iv_story;
        private final TextView title;
        private Story currentStory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_story = itemView.findViewById(R.id.iv_story);
            title = itemView.findViewById(R.id.title);

            iv_story.setOnClickListener(v ->{
                Intent intent = new Intent(itemView.getContext(), StoryDetails.class);
                intent.putExtra("story", currentStory.getImage());
                intent.putExtra("title", title.getText().toString());
                intent.putExtra("time", currentStory.getTime());
                itemView.getContext().startActivity(intent);
            });
        }

        public void setData(Story story){
            currentStory = story;
            iv_story.setImageResource(story.getImage());
            title.setText(story.getTitle());
        }
    }
}
