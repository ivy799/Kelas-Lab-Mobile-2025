package com.example.tp_4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ArrayList<Book> bookList = BookDataSource.Books;
    private ArrayList<Book> filteredList = new ArrayList<>(bookList);
    private BookAdapter adapter;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private ProgressBar progressBar;
    private Runnable searchRunnable;
    RecyclerView rvBooks;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        rvBooks = view.findViewById(R.id.rv_books);
        EditText etSearch = view.findViewById(R.id.search_book);
        progressBar = view.findViewById(R.id.progress_bar);
        rvBooks.setHasFixedSize(true);


        adapter = new BookAdapter(filteredList);
        rvBooks.setAdapter(adapter);


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                progressBar.setVisibility(View.VISIBLE);
                rvBooks.setVisibility(View.GONE);

                mainHandler.postDelayed(()->{
                    filterBooks(charSequence.toString());
                    progressBar.setVisibility(View.GONE);
                    rvBooks.setVisibility(View.VISIBLE);

                }, 2000);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;

    }

    private void filterBooks(String query) {
        filteredList.clear();

        if (query.isEmpty()) {
            filteredList.addAll(bookList);
        } else {
            for (Book book : bookList) {
                if (book.getJudul().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(book);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

}