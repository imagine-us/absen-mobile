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
    TextView txtNip, txtNama, logout;
    String nip,id,nama, foto;
    private SharedPreferences sp;

    private static final String NIP_KEY = "key_nip";
    private static final String ID_KEY = "key_id";
    private static final String NAMA_KEY = "key_nama";
    private static final String FOTO_KEY = "key_foto";
    private static final String KEEP_LOGIN_KEY = "key_keep_login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_baru_layout);

        this.sp = this.getSharedPreferences("sp", Context.MODE_PRIVATE);
        txtNip = findViewById(R.id.nipProfil);
        txtNama = findViewById(R.id.namaProfil);
        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.remove(NIP_KEY);
                editor.remove(ID_KEY);
                editor.remove(NAMA_KEY);
                editor.remove(FOTO_KEY);
                editor.remove(KEEP_LOGIN_KEY);
                editor.apply();
                startActivity(new Intent(MainActivity.this, SplashScreen.class));
                finish();
            }
        });
        //nip = getIntent().getExtras().getString("nip");
        //id = getIntent().getExtras().getString("id");
        //nama = getIntent().getExtras().getString("nama");
        nip = this.sp.getString("key_nip",null);
        id = this.sp.getString("key_id",null);
        nama = this.sp.getString("key_nama",null);

        txtNip.setText(nip);
        txtNama.setText(nama);


        profil = findViewById(R.id.imageProfil);
        Picasso.with(this).load(this.sp.getString("key_foto",null)).placeholder(R.drawable.icon_profile).transform(new PicassoCircleTransformation()).into(profil);

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
                //Toast.makeText(MainActivity.this,"Fitur belum tersedia", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this,PengaduanActivity.class));
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
                startActivity(new Intent(MainActivity.this,BawahanActivity.class));
//                Toast.makeText(MainActivity.this,"Fitur belum tersedia", Toast.LENGTH_LONG).show();
            }
        });

        agenda = findViewById(R.id.iconAgenda);
        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AgendaActivity.class));
               // Toast.makeText(MainActivity.this,"Fitur belum tersedia", Toast.LENGTH_LONG).show();
            }
        });

    }



}
