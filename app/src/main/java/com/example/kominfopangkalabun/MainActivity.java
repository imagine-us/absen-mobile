package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Button btnTambahTugas;
    ImageView profil, presensi, pekerjaan, pengaduan, tpp;
    TextView txtNip;
    String nip;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        txtNip = findViewById(R.id.nipProfil);
        nip = getIntent().getExtras().getString("nip");
        txtNip.setText(nip);

        profil = findViewById(R.id.imageProfil);
        Picasso.with(this).load(R.drawable.rudiantara).transform(new PicassoCircleTransformation()).into(profil);

        presensi = findViewById(R.id.iconPresensi);
        presensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,PresensiActivity.class);
                i.putExtra("nip",nip);
                startActivity(i);
            }
        });

        pekerjaan = findViewById(R.id.iconPekerjaan);
        pekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PekerjaanActivity.class));
            }
        });

        pengaduan = findViewById(R.id.iconPengaduan);
        pengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PengaduanActivity.class));
            }
        });


        tpp = findViewById(R.id.iconTpp);
        tpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TppActivity.class));
            }
        });

    }



}
