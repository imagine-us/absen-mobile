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

        uraian = findViewById(R.id.hsilUraianTugas2);
        suburaian = findViewById(R.id.hsilSubUraianTugas2);
        tanggal = findViewById(R.id.hsilTanggalKerja2);
        waktu = findViewById(R.id.hsilJam2);
        durasi = findViewById(R.id.hsilDurasi2);
        judul = findViewById(R.id.hsilNamaPekerjaan2);
        detail = findViewById(R.id.hsilDetailPekerjaan2);
    }
}
