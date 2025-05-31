package com.example.tp_3;

import java.util.ArrayList;
import java.util.List;

public class MainFeedDataSource {
    public static ArrayList<MainFeed> MainFeeds = generateDummyMainFeeds();
    private static ArrayList<MainFeed> generateDummyMainFeeds(){
        ArrayList<MainFeed> MainFeeds = new ArrayList<>();
        MainFeeds.add(new MainFeed("Jevon ivander", "raihan", "Selamat pagi dunia", "jangan ganggu", "12h", R.drawable.image3, 10, 20, 30, R.drawable.image1));
        MainFeeds.add(new MainFeed("Jevon ivander thomas", "jvoon", "Selamat pagi dunia", "jangan ganggu", "12h", R.drawable.image2, 10, 20, 30, R.drawable.image2));
        MainFeeds.add(new MainFeed("Jevon ivander thomas", "jvoon", "Selamat pagi dunia", "jangan ganggu", "12h", R.drawable.image1, 10, 20, 30, R.drawable.image3));
        MainFeeds.add(new MainFeed("Jevon ivander thomas", "jvoon", "Selamat pagi dunia", "jangan ganggu", "12h", R.drawable.image1, 10, 20, 30, R.drawable.image2));
        MainFeeds.add(new MainFeed("Jevon ivander thomas", "jvoon", "Selamat pagi dunia", "jangan ganggu", "12h", R.drawable.image1, 10, 20, 30, R.drawable.image2));
        MainFeeds.add(new MainFeed("Jevon ivander thomas", "jvoon", "Selamat pagi dunia", "jangan ganggu", "12h", R.drawable.image1, 10, 20, 30, R.drawable.image2));
        MainFeeds.add(new MainFeed("Jevon ivander thomas", "jvoon", "Selamat pagi dunia", "jangan ganggu", "12h", R.drawable.image1, 10, 20, 30, R.drawable.image2));
        MainFeeds.add(new MainFeed("Jevon ivander thomas", "jvoon", "Selamat pagi dunia", "jangan ganggu", "12h", R.drawable.image1, 10, 20, 30, R.drawable.image2));


        return MainFeeds;
    }
}
