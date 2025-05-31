package com.example.tp_6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private List<Character> allCharacters = new ArrayList<>();
    private int currentPage = 1;
    Button btn_reload;


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

        apiService = RetrofitClient.getClient().create(ApiService.class);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btn_reload = findViewById(R.id.btn_reload);

        loadData(currentPage);

        btn_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtil.isNetworkAvailable(MainActivity.this)) {
                    btn_reload.setVisibility(View.GONE);
                    loadData(currentPage);
                } else {
                    Toast.makeText(MainActivity.this, "Jaringan tidak tersedia!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loadData(int page){

        Call<CharacterResponse> call = apiService.getCharacters(page);
        call.enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                if (response.isSuccessful()){
                    List<Character> characterList = response.body().getResults();
                    allCharacters.addAll(characterList);
                    if (characterAdapter == null) {
                        characterAdapter = new CharacterAdapter(allCharacters);
                        recyclerView.setAdapter(characterAdapter);

                        characterAdapter.setOnLoadMoreClickListener(() -> {
                            currentPage++;
                            loadData(currentPage);
                        });

                    } else {
                        characterAdapter.characterList.addAll(characterList);
                    }
                    characterAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "Tidak ada data lagi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CharacterResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                btn_reload.setVisibility(View.VISIBLE);
            }
        });
    }
}