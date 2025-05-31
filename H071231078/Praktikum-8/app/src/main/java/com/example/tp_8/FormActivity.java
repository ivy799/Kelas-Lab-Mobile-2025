package com.example.tp_8;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp_8.Database.DatabaseContract;
import com.example.tp_8.Database.NoteHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormActivity extends AppCompatActivity {


    public static final String EXTRA_NOTE = "extra_note";
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    public static final int REQUEST_UPDATE = 200;
    private EditText etTitle, etDesc;
    private Button btnSave, btnDelete;
    NoteHelper noteHelper;
    private Boolean isEdit = false;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTitle = findViewById(R.id.et_title);
        etDesc = findViewById(R.id.et_desc);
        btnSave = findViewById(R.id.btn_save);
        btnDelete = findViewById(R.id.btn_delete);

        noteHelper = NoteHelper.getInstance(getApplicationContext());
        noteHelper.open();

        note = getIntent().getParcelableExtra(EXTRA_NOTE);

        if (note != null) {
            isEdit = true;
        }else {
            note = new Note();
        }

        String actionBarTitle;
        String btnTitle;

        if (isEdit){
            actionBarTitle = "Edit Note";
            btnTitle = "Update";
            if (note != null){
                etTitle.setText(note.getTitle());
                etDesc.setText(note.getDescription());
            }

            btnDelete.setVisibility(View.VISIBLE);
        }else {
            actionBarTitle = "add note";
            btnTitle = "Save";
        }

        btnSave.setText(btnTitle);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnSave.setOnClickListener(View ->  saveNote());
        btnDelete.setOnClickListener(View ->  deleteNote());
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed(){
        if (isEdit){
            new AlertDialog.Builder(this)
                    .setTitle("Konfirmasi")
                    .setMessage("Apakah Anda yakin ingin membatalkan perubahan?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        Intent intent = new Intent(FormActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("Batal", (dialog,which)->{
                        dialog.dismiss();
                    }).show();
        }else {
            new AlertDialog.Builder(this)
                    .setTitle("Konfirmasi")
                    .setMessage("Apakah Anda yakin ingin kembali?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        Intent intent = new Intent(FormActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("Batal", (dialog,which)->{
                        dialog.dismiss();
                    }).show();
        }

    }

    private void saveNote() {
        String title = etTitle.getText().toString().trim();
        String desc = etDesc.getText().toString().trim();

        if (title.isEmpty()){
            etTitle.setError("Title cannot be empty");
        }

        if (desc.isEmpty()){
            etDesc.setError("Description cannot be empty");
        }

        note.setTitle(title);
        note.setDescription(desc);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_NOTE, note);

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.NotesColumns.TITLE, title);
        contentValues.put(DatabaseContract.NotesColumns.DESCRIPTION, desc);
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String formattedDate = sdf.format(date);
        contentValues.put(DatabaseContract.NotesColumns.TIME, formattedDate);

        if (isEdit){
            long result = noteHelper.update(String.valueOf(note.getId()), contentValues);
            if (result > 0){
                setResult(RESULT_UPDATE, intent);
                finish();
            }else {
                Toast.makeText(this, "Failed to update", Toast.LENGTH_SHORT).show();

            }
        }else {
            long result = noteHelper.insert(contentValues);
            if (result > 0){
                note.setId((int) result);
                setResult(RESULT_ADD, intent);
                finish();
            }else {
                Toast.makeText(this, "Failed to add", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void deleteNote() {
        if (note != null && note.getId() > 0){
            long result = noteHelper.deleteById(String.valueOf(note.getId()));
            if (result > 0){
                setResult(RESULT_DELETE);
                finish();
            }else {
                Toast.makeText(this.getApplicationContext(), "Failed to delete", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this.getApplicationContext(), "Invalid note id", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (noteHelper != null) {
            noteHelper.close();
        }
    }


}