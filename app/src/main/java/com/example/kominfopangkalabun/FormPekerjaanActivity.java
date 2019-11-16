package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormPekerjaanActivity extends AppCompatActivity {

    EditText edtTanggalKerja, edtJamKerja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pekerjaan);

        edtTanggalKerja = findViewById(R.id.edtTanggalKerja);
        edtJamKerja = findViewById(R.id.edtJamKerja);

        //set tanggal dan waktu sekarang
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = date.format(new Date());
        edtTanggalKerja.setText(currentDate);

        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        String currentTime = time.format(new Date());
        edtJamKerja.setText(currentTime);

        //generate spinner uraian tugas
        Spinner spinnerUraianTugas = findViewById(R.id.sprUraianTugas);

        //pakai rest server seharusnya
        String[] items = new String[] { "Perumusan kebijakan di bidang pengelolaan data statistik dan integrasi sistem informasi.", "Green Tea", "Black Tea" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        spinnerUraianTugas.setAdapter(adapter);
    }
}
