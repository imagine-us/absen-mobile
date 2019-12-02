package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MonitoringActivity extends AppCompatActivity {

    Button back;
    TextView kanan, kiri, bulan;
    String bulanSekarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

        back = findViewById(R.id.btnBackMonitoringPekerjaan);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MonitoringActivity.this, MainActivity.class));
            }
        });

        bulan = findViewById(R.id.bulanMonitoring);
        kanan =findViewById(R.id.monitorKanan);
        kiri = findViewById(R.id.monitorKiri);

        bulanSekarang = bulan.getText().toString();

        kanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulanSekarang=geserBulan("kanan",bulanSekarang);
                bulan.setText(bulanSekarang);
            }
        });
        kiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulanSekarang=geserBulan("kiri",bulanSekarang);
                bulan.setText(bulanSekarang);
            }
        });

    }

    private String geserBulan(String arah, String posisi){

        String[] bulan = {"JANUARI","FEBRUARI","MARET","APRIL","MEI","JUNI","JULI","AGUSTUS","SEPTEMBER","OKTOBER","NOVEMBER","DESEMBER"};

        int i=0;
        int current=0;
        while(bulan[i].equals(posisi)){
            current=0;
            i++;
            break;
        }

        if(current==11)current=-1;
        else if(current==0)current=12;

        if(arah.equals("kanan")){
            return bulan[current+1];
        }else return bulan[current-1];

    }
}
