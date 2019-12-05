package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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

import com.example.kominfopangkalabun.adapter.AgendaAdapter;
import com.example.kominfopangkalabun.model.Agenda.Agenda;
import com.example.kominfopangkalabun.model.Agenda.AgendaModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BaseApiService mApiService;
    LinearLayoutManager layoutManager;
    String nip,id,nama;
    private SharedPreferences sp;
    Button back;
    ImageView tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);


        this.sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
        nip = this.sp.getString("key_nip",null);
        id = this.sp.getString("key_id",null);
        nama = this.sp.getString("key_nama",null);

        mApiService = UtilsApi.getAPIService();

        Call<AgendaModel> call = mApiService.requestAgenda(id);
        call.enqueue(new Callback<AgendaModel>() {
            @Override
            public void onResponse(Call<AgendaModel> call, Response<AgendaModel> response) {
                if(response.isSuccessful()){
                    List<Agenda> agendaList = response.body().getListAgenda();
                    recyclerView= findViewById(R.id.rvAgenda);
                    layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    AgendaAdapter menuAdapter = new AgendaAdapter(agendaList);
                    recyclerView.setAdapter(menuAdapter);
                    recyclerView.setLayoutManager(layoutManager);
                }
                else{
                    Toast.makeText(AgendaActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AgendaModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });





        back=findViewById(R.id.btnBackAgenda);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AgendaActivity.this, MainActivity.class));
            }
        });



        tambah = findViewById(R.id.iconTambahAgenda);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FormAgendaActivity.class);
                startActivity(intent);
            }
        });



    }
}
