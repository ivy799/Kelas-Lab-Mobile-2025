package com.example.tp_4;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBookFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddBookFragment newInstance(String param1, String param2) {
        AddBookFragment fragment = new AddBookFragment();
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

    private static final int PICK_IMAGE_REQUEST = 1;
    ImageView ivCover;
    EditText etJudul, etPenulis, etTahunTerbit, etBlurb;
    private String currentImagePath;

    Integer rating;
    Boolean favorite;
    ArrayList<String> genre, review;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        String[] genres = {"Action", "Romance", "Mystery", "Fantasy", "Horror"};
        boolean[] selectedGenres = new boolean[genres.length];
        ArrayList<String> selectedList = new ArrayList<>();

        ivCover = view.findViewById(R.id.iv_cover);
        etJudul = view.findViewById(R.id.et_judul);
        etPenulis = view.findViewById(R.id.et_penulis);
        etTahunTerbit = view.findViewById(R.id.et_tahunTerbit);
        etBlurb = view.findViewById(R.id.et_blurb);
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        Button spGenre = view.findViewById(R.id.sp_genre);
        Button addBook = view.findViewById(R.id.add_book);

        spGenre.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Select Genres").setMultiChoiceItems(genres, selectedGenres, (dialog, which, isChecked) -> {
                if (isChecked){
                    selectedList.add(genres[which]);
                }else {
                    selectedList.remove(genres[which]);
                }
            }).setPositiveButton("OK", (dialog, which) -> {
               spGenre.setText(TextUtils.join(", ", selectedList));
            }).setNegativeButton("Cancel", null).show();
        });



        Random random = new Random();
        rating = random.nextInt(5) + 1;
        favorite = false;
        genre = selectedList;
        review = new ArrayList<>();

        ivCover.setOnClickListener(v -> openImageChooser());

        addBook.setOnClickListener(v -> {
            Book book = new Book(
                    etJudul.getText().toString(),
                    etPenulis.getText().toString(),
                    etTahunTerbit.getText().toString(),
                    etBlurb.getText().toString(),
                    currentImagePath,
                    rating,
                    favorite,
                    genre,
                    review

            );

            BookDataSource.Books.add(0, book);
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            MainFragment mainFragment = new MainFragment();
            fragmentTransaction.replace(R.id.fragment_container, mainFragment);
            fragmentTransaction.addToBackStack(null);
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
            fragmentTransaction.commit();
        });



        return view;

    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            handleSelectedImage(data.getData());
        }
    }
    private void handleSelectedImage(Uri imageUri) {
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ivCover.setImageBitmap(bitmap);
            currentImagePath = saveImageToStorage(bitmap);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String saveImageToStorage(Bitmap bitmap) {
        String fileName = "profile_" + System.currentTimeMillis() + ".jpg";
        try (FileOutputStream fos = requireContext().openFileOutput(fileName, MODE_PRIVATE)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}