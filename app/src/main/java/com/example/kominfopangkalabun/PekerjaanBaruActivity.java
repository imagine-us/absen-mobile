package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PekerjaanBaruActivity extends AppCompatActivity {

    Button btnpekerjaanSemua, btnpekerjaanDiterima, btnpekerjaanDitolak, btnpekerjaanBelumDikoreksi, btnKembali;
    View vwpekerjaanSemua, vwpekerjaanDiterima, vwpekerjaanDitolak, vwpekerjaanBelumDikoreksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pekerjaan_baru);


        btnKembali = findViewById(R.id.btnBackMonitoring);
        btnpekerjaanSemua = findViewById(R.id.daftarPekerjaanAll);
        btnpekerjaanDiterima = findViewById(R.id.daftarPekerjaanDiterima);
        btnpekerjaanDitolak = findViewById(R.id.daftarPekerjaanDitolak);
        btnpekerjaanBelumDikoreksi = findViewById(R.id.daftarPekerjaanBelumDikoreksi);
        vwpekerjaanSemua = findViewById(R.id.viewPekerjaanAll);
        vwpekerjaanDiterima = findViewById(R.id.viewPekerjaanDiterima);
        vwpekerjaanDitolak = findViewById(R.id.viewPekerjaanDitolak);
        vwpekerjaanBelumDikoreksi = findViewById(R.id.viewPekerjaanBelumDikoreksi);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
