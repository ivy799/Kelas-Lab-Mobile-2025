package com.example.tp_8;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tp_8.Database.NoteHelper;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ADD = 100;
    private static final int REQUEST_UPDATE = 200;
    private RecyclerView rvNote;
    private TextView noData;
    private SearchView searchBar;
    private ExtendedFloatingActionButton fabAdd;
    private NoteAdapter noteAdapter;
    private NoteHelper noteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Notes");
        }

        rvNote = findViewById(R.id.rv_notes);
        noData = findViewById(R.id.noData);
        fabAdd = findViewById(R.id.fab_add);
        searchBar = findViewById(R.id.search_bar);

        rvNote.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(this);
        rvNote.setAdapter(noteAdapter);
        noteHelper = NoteHelper.getInstance(getApplicationContext());

        fabAdd.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivityForResult(intent, REQUEST_ADD);
        });

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String s) {
                 search_note(s);
                 return true;
             }

             @Override
             public boolean onQueryTextChange(String s) {
                 search_note(s);
                 return true;
             }
         });

        noteHelper.open();
        loadNotes();
    }

    private void search_note(String keyword){
        Cursor cursor = noteHelper.search(keyword);
        ArrayList<Note> notes = MappingHelper.mapCursorToArrayList(cursor);
        noteAdapter.setListNotes(notes);
        cursor.close();
    }

    private void loadNotes() {
        new LoadNotesAsync(this, notes -> {
            if (notes.size() > 0) {
                noteAdapter.setListNotes(notes);
                noData.setVisibility(View.GONE);
            } else {
                noteAdapter.setListNotes(new ArrayList<>());
                noData.setVisibility(View.VISIBLE);
                showToast("No data available");
            }
        }).execute();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_ADD) {
            if (resultCode == FormActivity.RESULT_ADD) {
                showToast("Note added successfully");
                loadNotes();
            }
        } else if (requestCode == REQUEST_UPDATE) {
            if (resultCode == FormActivity.RESULT_UPDATE) {
                showToast("Note updated successfully");
                loadNotes();
            } else if (resultCode == FormActivity.RESULT_DELETE) {
                showToast("Note deleted successfully");
                loadNotes();
            }
        }
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (noteHelper != null) {
            noteHelper.close();
        }
    }

    private static class LoadNotesAsync {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadNotesCallback> weakCallback;

        private LoadNotesAsync(Context context, LoadNotesCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                Context context = weakContext.get();
                if (context != null) {
                    NoteHelper studentHelper = NoteHelper.getInstance(context);
                    studentHelper.open();

                    Cursor notesCursor = studentHelper.queryAll();
                    ArrayList<Note> notes = MappingHelper.mapCursorToArrayList(notesCursor);

                    notesCursor.close();

                    handler.post(() -> {
                        LoadNotesCallback callback = weakCallback.get();
                        if (callback != null) {
                            callback.postExecute(notes);
                        }
                    });
                }
            });
        }
    }


    interface LoadNotesCallback {
        void postExecute(ArrayList<Note> notes);
    }
}