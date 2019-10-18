package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AbsensiActivity extends AppCompatActivity {

    Button btnAmbilGambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);

        btnAmbilGambar=findViewById(R.id.btnAmbilGambar);

        btnAmbilGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AbsensiActivity.this, MainActivity.class));
            }
        });
    }
}
