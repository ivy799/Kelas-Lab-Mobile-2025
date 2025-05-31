package com.example.tp_3;

import java.util.ArrayList;

public class ProfileFeedDataSource {
    public static ArrayList<ProfileFeed> ProfileFeeds = generateDummyProfileFeeds();
    private static ArrayList<ProfileFeed> generateDummyProfileFeeds(){
        ArrayList <ProfileFeed> ProfileFeeds = new ArrayList<>();
        ProfileFeeds.add(new ProfileFeed("raihan", "hello kalian", "10", "20", "30", "25 march 2025 • See translation", R.drawable.image2));
        ProfileFeeds.add(new ProfileFeed("raihan", "hello kalian", "10", "20", "30", "25 march 2025 • See translation", R.drawable.image1));
        ProfileFeeds.add(new ProfileFeed("raihan", "hello kalian", "10", "20", "30", "25 march 2025 • See translation", R.drawable.image3));
        ProfileFeeds.add(new ProfileFeed("raihan", "hello kalian", "10", "20", "30", "25 march 2025 • See translation", R.drawable.image2));

        return ProfileFeeds;
    }
}
