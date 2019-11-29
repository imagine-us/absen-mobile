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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kominfopangkalabun.adapter.AbsensiAdapter;
//import com.example.kominfopangkalabun.model.Absensi;
import com.example.kominfopangkalabun.model.Absensi.Absensi;
import com.example.kominfopangkalabun.model.Absensi.AbsensiModel;
import com.example.kominfopangkalabun.model.Absensi.CekAbsensi;
import com.example.kominfopangkalabun.model.Absensi.CekAbsensiModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresensiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BaseApiService mApiService, mApiService2;
    LinearLayoutManager layoutManager;
    ImageView profil, tambahPresensi;
    String nip,id,nama;
    TextView txtNip,txtNama;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presensi);

        this.sp = this.getSharedPreferences("sp", Context.MODE_PRIVATE);
        txtNip = findViewById(R.id.nipProfilPresensi);
        txtNama = findViewById(R.id.namaProfilPresensi);
        nip = this.sp.getString("key_nip",null);
        id = this.sp.getString("key_id",null);
        nama = this.sp.getString("key_nama",null);

        txtNip.setText(nip);
        txtNama.setText(nama);


        mApiService = UtilsApi.getAPIService();
        mApiService2 = UtilsApi.getAPIService();
        tambahPresensi = findViewById(R.id.iconTambahPresensi);
        tambahPresensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiService2.requestCekAbsensi(id).enqueue(new Callback<CekAbsensiModel>() {
                    @Override
                    public void onResponse(Call<CekAbsensiModel> call, Response<CekAbsensiModel> response) {
//
                         List<CekAbsensi> listcekabsensi = response.body().getListCekAbsensi();
                         String status = response.body().getStatusCekAbsensi();

                        if(status.equals("true")){
                            Intent i = new Intent(PresensiActivity.this, FormAbsensiActivity.class);
                            String statusid  = listcekabsensi.get(0).getStatusId();
                            i.putExtra("st_id",statusid);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(PresensiActivity.this,"Anda tidak dapat absen untuk hari ini",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CekAbsensiModel> call, Throwable t) {
                        Toast.makeText(PresensiActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

        profil = findViewById(R.id.imageProfilPresensi);
        Picasso.with(this).load(this.sp.getString("key_foto",null)).transform(new PicassoCircleTransformation()).into(profil);

        Call<AbsensiModel> call = mApiService.requestAbsensiHistori(id);
        call.enqueue(new Callback<AbsensiModel>() {
            @Override
            public void onResponse(Call<AbsensiModel> call, Response<AbsensiModel> response) {
                if(response.isSuccessful()){
                List<Absensi> absensiList = response.body().getListAbsensi();
                recyclerView= findViewById(R.id.rv);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                AbsensiAdapter menuAdapter = new AbsensiAdapter(absensiList);
                recyclerView.setAdapter(menuAdapter);
                recyclerView.setLayoutManager(layoutManager);
                }
                else{
                    Toast.makeText(PresensiActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AbsensiModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }
}
