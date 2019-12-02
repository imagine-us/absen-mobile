package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MonitoringActivity extends AppCompatActivity {

    Button back;
    TextView kanan, kiri, bulan;
    String bulanSekarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);


        final String[] daftarBulan = {"JANUARI", "FEBRUARI", "MARET", "APRIL", "MEI", "JUNI", "JULI", "AGUSTUS", "SEPTEMBER", "OKTOBER", "NOVEMBER", "DESEMBER"};


        back = findViewById(R.id.btnBackMonitoringPekerjaan);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MonitoringActivity.this, MainActivity.class));
            }
        });

        bulan = findViewById(R.id.bulanMonitoring);
        kanan = findViewById(R.id.monitorKanan);
        kiri = findViewById(R.id.monitorKiri);



        kanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulanSekarang = bulan.getText().toString();
                int i=indexBulan(bulanSekarang);
                if(i==11)i=-1;
                bulan.setText(daftarBulan[i+1]);
            }
        });
        kiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulanSekarang = bulan.getText().toString();
                int i=indexBulan(bulanSekarang);
                if(i==0)i=12;
                bulan.setText(daftarBulan[i-1]);
            }
        });

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


}
