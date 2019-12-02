package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Button btnTambahTugas, presensi, pekerjaan, pengaduan, tpp, monitoring, agenda;
    ImageView fotoprofil,profil;
    TextView txtNip, txtNama;
    String nip,id,nama, foto;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_baru_layout);

        this.sp = this.getSharedPreferences("sp", Context.MODE_PRIVATE);
        txtNip = findViewById(R.id.nipProfil);
        txtNama = findViewById(R.id.namaProfil);

        //nip = getIntent().getExtras().getString("nip");
        //id = getIntent().getExtras().getString("id");
        //nama = getIntent().getExtras().getString("nama");
        nip = this.sp.getString("key_nip",null);
        id = this.sp.getString("key_id",null);
        nama = this.sp.getString("key_nama",null);

        txtNip.setText(nip);
        txtNama.setText(nama);


        profil = findViewById(R.id.imageProfil);
        Picasso.with(this).load(this.sp.getString("key_foto",null)).transform(new PicassoCircleTransformation()).into(profil);

        presensi = findViewById(R.id.iconPresensi);
        presensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,PresensiBaruActivity.class);
                startActivity(i);
            }
        });

        pekerjaan = findViewById(R.id.iconPekerjaan);
        pekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Fitur belum tersedia", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this,PekerjaanBaruActivity.class));
            }
        });

        pengaduan = findViewById(R.id.iconPengaduan);
        pengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Fitur belum tersedia", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(MainActivity.this,PengaduanActivity.class));
            }
        });


        tpp = findViewById(R.id.iconTpp);
        tpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Fitur belum tersedia", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(MainActivity.this,TppActivity.class));
            }
        });

        monitoring = findViewById(R.id.iconMonitoring);
        monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MonitoringActivity.class));
//                Toast.makeText(MainActivity.this,"Fitur belum tersedia", Toast.LENGTH_LONG).show();
            }
        });

        agenda = findViewById(R.id.iconAgenda);
        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Fitur belum tersedia", Toast.LENGTH_LONG).show();
            }
        });

    }



}
