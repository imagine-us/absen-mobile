package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailMonitoringActivity extends AppCompatActivity {


    TextView uraian,suburaian,tanggal,waktu,durasi,judul,detail;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_monitoring);

        back = findViewById(R.id.btnBackMonitoringPekerjaan);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        uraian = findViewById(R.id.hsilUraianTugas);
        suburaian = findViewById(R.id.hsilSubUraianTugas);
        tanggal = findViewById(R.id.hsilTanggalKerja);
        waktu = findViewById(R.id.hsilJam);
        durasi = findViewById(R.id.hsilDurasi);
        judul = findViewById(R.id.hsilNamaPekerjaan);
        detail = findViewById(R.id.hsilDetailPekerjaan);
    }
}
