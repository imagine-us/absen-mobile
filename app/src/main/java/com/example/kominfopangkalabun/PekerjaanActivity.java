package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PekerjaanActivity extends AppCompatActivity {

    ImageView tambahPekerjaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pekerjaan);

        tambahPekerjaan =findViewById(R.id.iconTambahPekerjaan);



        tambahPekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FormPekerjaanActivity.class);
                startActivity(intent);
            }
        });
    }
}
