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
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.util.ArrayList;
import java.util.List;

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
                Intent intent = new Intent(getApplicationContext(), FormPengaduanActivity.class);
                startActivity(intent);
            }
        });

//        Pengaduan pengaduan = new Pengaduan("0","17-09-2019");
        List<Pengaduan> pengaduanList = new ArrayList<>();
        pengaduanList.add(new Pengaduan("1","17-09-2019"));
        Log.e("Tes","" + pengaduanList.get(0).getPengaduanStatus());

        recyclerView= findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        PengaduanAdapter menuAdapter = new PengaduanAdapter(pengaduanList);
        recyclerView.setAdapter(menuAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}
