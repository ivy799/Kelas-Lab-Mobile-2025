package com.example.tp_3;

import java.util.ArrayList;

public class StroryDataSource {
    public static ArrayList<Story> Stories = generateDummyStories();

    public static ArrayList<Story> generateDummyStories(){
        ArrayList<Story> stories = new ArrayList<>();

        stories.add(new Story("New", R.drawable.plus, "2 March"));
        stories.add(new Story("Work 2", R.drawable.image2, "12 March"));
        stories.add(new Story("Work 3", R.drawable.image3, "12 March"));
        stories.add(new Story("Work 4", R.drawable.image1, "12 March"));
        stories.add(new Story("Work 5", R.drawable.image2, "12 March"));
        stories.add(new Story("Work 6", R.drawable.image3, "12 March"));
        stories.add(new Story("Work 7", R.drawable.image1, "12 March"));

        return stories;
    }
}
