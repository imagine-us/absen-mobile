package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.kominfopangkalabun.adapter.AbsensiAdapter;
import com.example.kominfopangkalabun.adapter.PengaduanAdapter;
import com.example.kominfopangkalabun.model.Pengaduan.Pengaduan;
import com.example.kominfopangkalabun.model.Pengaduan.PengaduanModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengaduanActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BaseApiService mApiService;
    LinearLayoutManager layoutManager;
    ImageView tambahPengaduan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);


        mApiService = UtilsApi.getAPIService();
        tambahPengaduan = findViewById(R.id.iconTambahPengaduan);
        tambahPengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), FormPengaduanActivity.class);
                startActivity(intent);
            }
        });


        Call<PengaduanModel> call = mApiService.requestPengaduan("2");
        call.enqueue(new Callback<PengaduanModel>() {
            @Override
            public void onResponse(Call<PengaduanModel> call, Response<PengaduanModel> response) {
                List<Pengaduan> pengaduanList = response.body().getListPengaduan();
                recyclerView= findViewById(R.id.rv);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                PengaduanAdapter menuAdapter = new PengaduanAdapter(pengaduanList);
                recyclerView.setAdapter(menuAdapter);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<PengaduanModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });


    }
}
