package com.example.tp_3;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class ProfileFeedAdapter extends RecyclerView.Adapter<ProfileFeedAdapter.ViewHolder>{

    private final ArrayList<ProfileFeed> ProfileFeeds;

    public ProfileFeedAdapter(ArrayList<com.example.tp_3.ProfileFeed> profileFeed) {
        ProfileFeeds = profileFeed;
    }

    @NonNull
    @Override
    public ProfileFeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_feed_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileFeedAdapter.ViewHolder holder, int position) {
        com.example.tp_3.ProfileFeed profileFeed = ProfileFeeds.get(position);
        holder.setData(profileFeed);
    }

    @Override
    public int getItemCount() {
        return ProfileFeeds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView iv_profile;
        private ProfileFeed currentProfileFeed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_profile = itemView.findViewById(R.id.iv_profile);
            iv_profile.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), ProfileFeedDetails.class);
                if (currentProfileFeed.hasImageUri()) {
                    intent.putExtra("image", currentProfileFeed.getImageUri());
                } else {
                    intent.putExtra("image", currentProfileFeed.getImage());
                }
                intent.putExtra("likeCount", currentProfileFeed.getLikes());
                intent.putExtra("commentCount", currentProfileFeed.getComments());
                intent.putExtra("shareCount", currentProfileFeed.getShares());
                intent.putExtra("feed", currentProfileFeed.getFeed());
                intent.putExtra("time", currentProfileFeed.getTime());
                itemView.getContext().startActivity(intent);
            });
        }

        public void setData(ProfileFeed profileFeed) {
            currentProfileFeed = profileFeed;

            if (profileFeed.hasImageUri()) {
                Log.d("ProfileFeedAdapter", "Image URI: " + profileFeed.getImageUri());
                System.out.println(profileFeed.getImageUri());

                // Ambil file dari internal storage
                File imageFile = new File(iv_profile.getContext().getFilesDir(), profileFeed.getImageUri());

                // Convert ke content:// URI pakai FileProvider
                Uri imageUri = FileProvider.getUriForFile(iv_profile.getContext(),
                        "com.example.tp_3.fileprovider", imageFile);

                // Load ke ImageView pakai Glide
                Glide.with(iv_profile.getContext())
                        .load(imageUri)
                        .into(iv_profile);
            } else {
                iv_profile.setImageResource(profileFeed.getImage());
            }
        }


    }

}
