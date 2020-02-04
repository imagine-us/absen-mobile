package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class DetailAgendaActivity extends AppCompatActivity {

    Button kembali;
    TextView isiAgenda, tanggalAgenda;
    BaseApiService mApiService;
    String idagenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_agenda);

        kembali = findViewById(R.id.btnBackDetailAgenda);
        isiAgenda = findViewById(R.id.txtIsiDetailAgenda);
        tanggalAgenda = findViewById(R.id.txtTanggalDetailAgenda);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        idagenda = intent.getExtras().getString("idagenda");
        mApiService = UtilsApi.getAPIService();

        Call<AgendaModel> call = mApiService.requestAgendaDetail(idagenda);
        call.enqueue(new Callback<AgendaModel>() {
            @Override
            public void onResponse(Call<AgendaModel> call, Response<AgendaModel> response) {
                if(response.isSuccessful()){
                    List<Agenda> agendaList = response.body().getListAgenda();
                    tanggalAgenda.setText("Tanggal Agenda : " + agendaList.get(0).getTanggal());
                    isiAgenda.setText(agendaList.get(0).getIsiagenda());
                }
                else{
                    Toast.makeText(DetailAgendaActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AgendaModel> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }
}
