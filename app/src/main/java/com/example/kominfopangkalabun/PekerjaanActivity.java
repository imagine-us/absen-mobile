package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.kominfopangkalabun.adapter.PekerjaanAdapter;
import com.example.kominfopangkalabun.model.Pekerjaan.Pekerjaan;
import com.example.kominfopangkalabun.model.Pekerjaan.PekerjaanModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PekerjaanActivity extends AppCompatActivity {

    ImageView tambahPekerjaan;
    RecyclerView recyclerView;
    BaseApiService mApiService;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pekerjaan);

        tambahPekerjaan =findViewById(R.id.iconTambahPekerjaan);



        tambahPekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FormPekerjaanActivity.class);
                startActivity(intent);
            }
        });

        String id  = "123";

        Call<PekerjaanModel> call = mApiService.requestPekerjaanHistory(id, "11");
        call.enqueue(new Callback<PekerjaanModel>() {
            @Override
            public void onResponse(Call<PekerjaanModel> call, Response<PekerjaanModel> response) {
                List<Pekerjaan> pekerjaanList = response.body().getPekerjaanModelList();
                recyclerView= findViewById(R.id.rv);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                PekerjaanAdapter menuAdapter = new PekerjaanAdapter(pekerjaanList);
                recyclerView.setAdapter(menuAdapter);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<PekerjaanModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }
}
