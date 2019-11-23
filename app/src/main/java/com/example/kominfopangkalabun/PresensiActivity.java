package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kominfopangkalabun.adapter.AbsensiAdapter;
//import com.example.kominfopangkalabun.model.Absensi;
import com.example.kominfopangkalabun.model.Absensi.Absensi;
import com.example.kominfopangkalabun.model.Absensi.AbsensiModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresensiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
//    Absensi absensi;
//    List<Absensi> absensiList = new ArrayList<>();
    BaseApiService mApiService;
    LinearLayoutManager layoutManager;
    ImageView profil, tambahPresensi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presensi);

        mApiService = UtilsApi.getAPIService();
        tambahPresensi = findViewById(R.id.iconTambahPresensi);
        tambahPresensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(PresensiActivity.this,"tes",Toast.LENGTH_LONG).show();
                startActivity(new Intent(PresensiActivity.this, FormAbsensiActivity.class));
            }
        });

        profil = findViewById(R.id.imageProfilPresensi);
        Picasso.with(this).load(R.drawable.rudiantara).transform(new PicassoCircleTransformation()).into(profil);

        Call<AbsensiModel> call = mApiService.requestAbsensiHistori("2");
        call.enqueue(new Callback<AbsensiModel>() {
            @Override
            public void onResponse(Call<AbsensiModel> call, Response<AbsensiModel> response) {
                List<Absensi> absensiList = response.body().getListAbsensi();
                recyclerView= findViewById(R.id.rv);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                AbsensiAdapter menuAdapter = new AbsensiAdapter(absensiList);
                recyclerView.setAdapter(menuAdapter);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<AbsensiModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });

//        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
//        absensiList.add(absensi);
//
//        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
//        absensiList.add(absensi);
//
//        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
//        absensiList.add(absensi);
//
//        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
//        absensiList.add(absensi);
//
//        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
//        absensiList.add(absensi);
//
//        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
//        absensiList.add(absensi);
//
//        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
//        absensiList.add(absensi);
//
//        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
//        absensiList.add(absensi);
//
//        absensi = new Absensi("12/12/2019","07:00","112","110","Alun-alun, Pangkalabun","1101101");
//        absensiList.add(absensi);

//        recyclerView= findViewById(R.id.rv);
//        layoutManager = new LinearLayoutManager(this);
//        AbsensiAdapter menuAdapter = new AbsensiAdapter(absensiList);
//        recyclerView.setAdapter(menuAdapter);
//        recyclerView.setLayoutManager(layoutManager);


    }
}
