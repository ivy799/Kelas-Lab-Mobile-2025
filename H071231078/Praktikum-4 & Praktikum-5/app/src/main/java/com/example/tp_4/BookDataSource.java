package com.example.tp_4;

import java.util.ArrayList;
import java.util.Arrays;

public class BookDataSource {
    public static ArrayList<Book> Books = generateDummyData();
    public static ArrayList<Book> FavoriteBooks = new ArrayList<>();
    public static ArrayList<Book> generateDummyData(){
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(
                "Atomic Habits",
                "James Clear",
                "2018",
                "Orang mengira ketika Anda ingin mengubah hidup, Anda perlu memikirkan hal-hal besar. Namun pakar kebiasaan terkenal kelas dunia James Clear telah menemukan sebuah cara lain",
                R.drawable.atomichabbit,
                5,
                false,
                new ArrayList<>(Arrays.asList("self-help", "self-improvement")),
                new ArrayList<>(Arrays.asList("good", "fantastic"))
        ));

        books.add(new Book(
                "The Psychology of Money",
                "Morgan Housel",
                "2020",
                "Buku ini membahas bagaimana cara orang berpikir tentang uang dan bagaimana emosi memengaruhi keputusan finansial.",
                R.drawable.psychologyofmoney,
                4,
                false,
                new ArrayList<>(Arrays.asList("finance", "psychology")),
                new ArrayList<>(Arrays.asList("insightful", "valuable"))
        ));

        books.add(new Book(
                "Deep Work",
                "Cal Newport",
                "2016",
                "Deep Work adalah tentang bagaimana fokus tanpa gangguan dapat menghasilkan hasil yang luar biasa dalam kehidupan profesional Anda.",
                R.drawable.deepwork,
                5,
                false,
                new ArrayList<>(Arrays.asList("productivity", "self-help")),
                new ArrayList<>(Arrays.asList("life-changing", "must-read"))
        ));

        books.add(new Book(
                "Start With Why",
                "Simon Sinek",
                "2009",
                "Buku ini mengajarkan bahwa pemimpin yang besar memulai dari 'mengapa' untuk menginspirasi orang lain.",
                R.drawable.startwithwhy,
                4,
                false,
                new ArrayList<>(Arrays.asList("leadership", "motivation")),
                new ArrayList<>(Arrays.asList("inspirational", "thought-provoking"))
        ));

        books.add(new Book(
                "Atomic Habits",
                "James Clear",
                "2018",
                "Orang mengira ketika Anda ingin mengubah hidup, Anda perlu memikirkan hal-hal besar. Namun pakar kebiasaan terkenal kelas dunia James Clear telah menemukan sebuah cara lain",
                R.drawable.atomichabbit,
                5,
                false,
                new ArrayList<>(Arrays.asList("self-help", "self-improvement")),
                new ArrayList<>(Arrays.asList("good", "fantastic"))
        ));

        books.add(new Book(
                "The Psychology of Money",
                "Morgan Housel",
                "2020",
                "Buku ini membahas bagaimana cara orang berpikir tentang uang dan bagaimana emosi memengaruhi keputusan finansial.",
                R.drawable.psychologyofmoney,
                4,
                false,
                new ArrayList<>(Arrays.asList("finance", "psychology")),
                new ArrayList<>(Arrays.asList("insightful", "valuable"))
        ));

        books.add(new Book(
                "Deep Work",
                "Cal Newport",
                "2016",
                "Deep Work adalah tentang bagaimana fokus tanpa gangguan dapat menghasilkan hasil yang luar biasa dalam kehidupan profesional Anda.",
                R.drawable.deepwork,
                5,
                false,
                new ArrayList<>(Arrays.asList("productivity", "self-help")),
                new ArrayList<>(Arrays.asList("life-changing", "must-read"))
        ));

        books.add(new Book(
                "Start With Why",
                "Simon Sinek",
                "2009",
                "Buku ini mengajarkan bahwa pemimpin yang besar memulai dari 'mengapa' untuk menginspirasi orang lain.",
                R.drawable.startwithwhy,
                4,
                false,
                new ArrayList<>(Arrays.asList("leadership", "motivation")),
                new ArrayList<>(Arrays.asList("inspirational", "thought-provoking"))
        ));
        return books;
    }
}
