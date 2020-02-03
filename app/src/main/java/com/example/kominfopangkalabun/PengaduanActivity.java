package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
    Button back;
    String id;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);
        this.sp = this.getSharedPreferences("sp", Context.MODE_PRIVATE);
        id = this.sp.getString("key_id",null);

        back = findViewById(R.id.btnBackPengaduan);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PengaduanActivity.this,MainActivity.class));
            }
        });

        mApiService = UtilsApi.getAPIService();
        tambahPengaduan = findViewById(R.id.iconTambahPengaduan);
        tambahPengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), FormPengaduanActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Call<PengaduanModel> call = mApiService.requestPengaduan(id);
        call.enqueue(new Callback<PengaduanModel>() {
            @Override
            public void onResponse(Call<PengaduanModel> call, Response<PengaduanModel> response) {
                if(response.isSuccessful()){
                    List<Pengaduan> pengaduanList = response.body().getListPengaduan();
                    recyclerView= findViewById(R.id.rvPengaduan);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    PengaduanAdapter menuAdapter = new PengaduanAdapter(pengaduanList);
                    recyclerView.setAdapter(menuAdapter);
                    recyclerView.setLayoutManager(layoutManager);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Pengaduan Masih Kosong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PengaduanModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }
}
