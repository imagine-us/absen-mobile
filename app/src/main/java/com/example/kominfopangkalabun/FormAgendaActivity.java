package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kominfopangkalabun.model.Agenda.AgendaModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAgendaActivity extends AppCompatActivity {

    EditText isiAgenda;
    Button simpanAgenda, kembaliAgenda;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private SharedPreferences sp;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_agenda);

        isiAgenda = findViewById(R.id.edtAgenda);
        simpanAgenda = findViewById(R.id.btnFormAgendaKirim);
        kembaliAgenda = findViewById(R.id.btnFormAgendaKembali);
        this.sp = this.getSharedPreferences("sp", Context.MODE_PRIVATE);
        mApiService = UtilsApi.getAPIService();

        kembaliAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        simpanAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String tanggalsekarang = df.format(c);
                String isiagenda = isiAgenda.getText().toString();
                String pnsid = sp.getString("key_id",null);

                Call<AgendaModel> call = mApiService.insertagenda(pnsid,isiagenda,tanggalsekarang);
                call.enqueue(new Callback<AgendaModel>() {
                    @Override
                    public void onResponse(Call<AgendaModel> call, Response<AgendaModel> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Membuat Agenda Berhasil",Toast.LENGTH_LONG);
                            Intent intent = new Intent(getApplicationContext(),AgendaActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Mohon maaf ada masalah.",Toast.LENGTH_LONG);
                        }
                    }

                    @Override
                    public void onFailure(Call<AgendaModel> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
            }
        });

    }
}
