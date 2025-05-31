package com.example.tp_6;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterDetailActivity extends AppCompatActivity {

    CircleImageView iv_profile;
    TextView tv_name, tv_status, tv_species, tv_gender;
    Button btn_reload;

    private ApiService apiService;
    private String id;
    private Handler networkHandler = new Handler();
    private Runnable networkRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_character_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        iv_profile = findViewById(R.id.iv_profile);
        tv_name = findViewById(R.id.tv_name);
        tv_status = findViewById(R.id.tv_status);
        tv_species = findViewById(R.id.tv_species);
        tv_gender = findViewById(R.id.tv_gender);
        btn_reload = findViewById(R.id.btn_reload);

        id = getIntent().getStringExtra("id");

        btn_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtil.isNetworkAvailable(CharacterDetailActivity.this)) {
                    btn_reload.setVisibility(View.GONE);
                    loadCharacter();
                } else {
                    Toast.makeText(CharacterDetailActivity.this, "Jaringan tidak tersedia!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loadCharacter();
        startNetworkMonitoring();
    }

    private void loadCharacter() {
        if (id != null) {
            try {
                int charId = Integer.parseInt(id);
                apiService = RetrofitClient.getClient().create(ApiService.class);
                Call<Character> call = apiService.getCharacter(charId);
                call.enqueue(new Callback<Character>() {
                    @Override
                    public void onResponse(Call<Character> call, Response<Character> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Character character = response.body();
                            Picasso.get().load(character.getImage()).into(iv_profile);
                            tv_name.setText(character.getName());
                            tv_status.setText(character.getStatus());
                            tv_species.setText(character.getSpecies());
                            tv_gender.setText(character.getGender());
                            btn_reload.setVisibility(View.GONE);
                        } else {
                            showReloadButton();
                        }
                    }

                    @Override
                    public void onFailure(Call<Character> call, Throwable t) {
                        showReloadButton();
                    }
                });
            } catch (NumberFormatException e) {
                e.printStackTrace();
                showReloadButton();
            }
        }
    }

    private void showReloadButton() {
        btn_reload.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Gagal memuat data. Cek koneksi lalu klik Reload.", Toast.LENGTH_SHORT).show();
    }

    // Monitoring jaringan secara berkala (setiap 2 detik)
    private void startNetworkMonitoring() {
        networkRunnable = new Runnable() {
            @Override
            public void run() {
                if (btn_reload.getVisibility() == View.VISIBLE && NetworkUtil.isNetworkAvailable(CharacterDetailActivity.this)) {
                    btn_reload.setVisibility(View.GONE);
                    loadCharacter();
                }
                networkHandler.postDelayed(this, 2000);
            }
        };
        networkHandler.post(networkRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkHandler.removeCallbacks(networkRunnable);
    }
}