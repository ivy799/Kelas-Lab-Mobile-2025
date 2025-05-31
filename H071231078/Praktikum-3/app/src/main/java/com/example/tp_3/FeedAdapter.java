package com.example.tp_3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder>{

    private final ArrayList<MainFeed> MainFeed;

    public FeedAdapter(ArrayList<com.example.tp_3.MainFeed> mainFeed) {
        MainFeed = mainFeed;
    }

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_layout, parent, false);
        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.ViewHolder holder, int position) {
        MainFeed mainFeed = MainFeed.get(position);
        holder.setData(mainFeed);
    }


    @Override
    public int getItemCount() {
         return MainFeed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView iv_profile;
        private final TextView username;
        private final TextView feed;
        private final TextView hour;
        private final TextView likeCount;
        private final TextView commentCount;
        private final TextView shareCount;
        private MainFeed currentFeed;
        private final ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_profile = itemView.findViewById(R.id.iv_profile);
            this.username = itemView.findViewById(R.id.nickname);
            this.feed = itemView.findViewById(R.id.feed);
            this.hour = itemView.findViewById(R.id.time);
            this.likeCount = itemView.findViewById(R.id.likeCount);
            this.commentCount = itemView.findViewById(R.id.CommentCount);
            this.shareCount = itemView.findViewById(R.id.shareCount);
            this.image = itemView.findViewById(R.id.image);

            iv_profile.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), ProfileDetails.class);
                intent.putExtra("profile", currentFeed.getProfile());
                intent.putExtra("name", currentFeed.getName());
                intent.putExtra("username", username.getText().toString());
                intent.putExtra("feed", feed.getText().toString());
                intent.putExtra("hour", hour.getText().toString());
                intent.putExtra("likeCount", likeCount.getText().toString());
                intent.putExtra("commentCount", commentCount.getText().toString());
                intent.putExtra("shareCount", shareCount.getText().toString());
                intent.putExtra("image", currentFeed.getImage());

                itemView.getContext().startActivity(intent);
            });

            username.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), ProfileDetails.class);
                intent.putExtra("profile", currentFeed.getProfile());
                intent.putExtra("name", currentFeed.getName());
                intent.putExtra("username", username.getText().toString());
                intent.putExtra("feed", feed.getText().toString());
                intent.putExtra("hour", hour.getText().toString());
                intent.putExtra("likeCount", likeCount.getText().toString());
                intent.putExtra("commentCount", commentCount.getText().toString());
                intent.putExtra("shareCount", shareCount.getText().toString());
                intent.putExtra("image", currentFeed.getImage());

                itemView.getContext().startActivity(intent);
            });
        }

        public void setData(MainFeed mainFeed){
            currentFeed = mainFeed;
            iv_profile.setImageResource(mainFeed.getProfile());
            username.setText(mainFeed.getUsername());
            feed.setText(mainFeed.getFeed());
            hour.setText(String.valueOf(mainFeed.getTime()));
            likeCount.setText(String.valueOf(mainFeed.getLikes()));
            commentCount.setText(String.valueOf(mainFeed.getComments()));
            shareCount.setText(String.valueOf(mainFeed.getShares()));
            image.setImageResource(mainFeed.getImage());
        }

    }
}
