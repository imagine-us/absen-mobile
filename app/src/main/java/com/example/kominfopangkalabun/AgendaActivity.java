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
import android.widget.TextView;
import android.widget.Toast;

import com.example.kominfopangkalabun.adapter.AgendaAdapter;
import com.example.kominfopangkalabun.model.Agenda.Agenda;
import com.example.kominfopangkalabun.model.Agenda.AgendaModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.util.Calendar;
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
    TextView kanan, kiri, bulan;
    String bulanSekarang;
    int month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        month = Calendar.getInstance().get(Calendar.MONTH);

        final String[] daftarBulan = {"JANUARI", "FEBRUARI", "MARET", "APRIL", "MEI", "JUNI", "JULI", "AGUSTUS", "SEPTEMBER", "OKTOBER", "NOVEMBER", "DESEMBER"};

        this.sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
        nip = this.sp.getString("key_nip",null);
        id = this.sp.getString("key_id",null);
        nama = this.sp.getString("key_nama",null);

        mApiService = UtilsApi.getAPIService();

        bulan = findViewById(R.id.bulanAgenda);
        kanan = findViewById(R.id.kananAgenda);
        kiri = findViewById(R.id.kiriAgenda);

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

        bulan.setText(daftarBulan[month]);

        kanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bulanSekarang = bulan.getText().toString();
                int i=indexBulan(bulanSekarang);
                if(i==11){
                    i= 0;
                }
                else{
                    i = i+1;
                }
                bulan.setText(daftarBulan[i]);
                getData(String.valueOf(i+1));
                Toast.makeText(getApplicationContext(),"Bulan"+(i+1),Toast.LENGTH_SHORT).show();
            }
        });

        kiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bulanSekarang = bulan.getText().toString();
                int i=indexBulan(bulanSekarang);
                if(i==0){
                    i=11;
                }
                else{
                    i = i - 1;
                }
                bulan.setText(daftarBulan[i]);
                getData(String.valueOf(i+1));
                Toast.makeText(getApplicationContext(),"Bulan"+ (i+1),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData(String.valueOf(month));
    }

    public int indexBulan(String inputBulan) {
        switch (inputBulan) {
            case "JANUARI":
                return 0;
            case "FEBRUARI":
                return 1;
            case "MARET":
                return 2;
            case "APRIL":
                return 3;
            case "MEI":
                return 4;
            case "JUNI":
                return 5;
            case "JULI":
                return 6;
            case "AGUSTUS":
                return 7;
            case "SEPTEMBER":
                return 8;
            case "OKTOBER":
                return 9;
            case "NOVEMBER":
                return 10;
            case "DESEMBER":
                return 11;
            default:
                return 0;

        }
    }

    public void getData(String month){
        Call<AgendaModel> call = mApiService.requestAgenda(id, String.valueOf(month));
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
                    menuAdapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(AgendaActivity.this, "Tidak Ada Agenda Bulan Ini", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AgendaModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }
}
