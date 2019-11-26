package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresensiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BaseApiService mApiService;
    LinearLayoutManager layoutManager;
    ImageView profil, tambahPresensi;
    String nip,id,nama;
    TextView txtNip,txtNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presensi);

        txtNip = findViewById(R.id.nipProfilPresensi);
        txtNama = findViewById(R.id.namaProfilPresensi);
        nip = getIntent().getExtras().getString("nip");
        id = getIntent().getExtras().getString("id");
        nama = getIntent().getExtras().getString("nama");

        txtNip.setText(nip);
        txtNama.setText(nama);


        mApiService = UtilsApi.getAPIService();
        tambahPresensi = findViewById(R.id.iconTambahPresensi);
        tambahPresensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiService.cekAbsen(nip).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Intent i =new Intent(PresensiActivity.this, FormAbsensiActivity.class);
                        i.putExtra("nip",nip);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(PresensiActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

        profil = findViewById(R.id.imageProfilPresensi);
        Picasso.with(this).load(R.drawable.rudiantara).transform(new PicassoCircleTransformation()).into(profil);

        Call<AbsensiModel> call = mApiService.requestAbsensiHistori(id);
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
    }
}
