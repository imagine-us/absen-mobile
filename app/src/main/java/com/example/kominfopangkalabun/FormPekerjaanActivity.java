package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormPekerjaanActivity extends AppCompatActivity {

    EditText edtTanggalKerja, edtJamKerja, edtHasilKerja;
    Button btnKembaliPekerjaan;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
    SimpleDateFormat time = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pekerjaan);

        edtTanggalKerja = findViewById(R.id.edtTanggalKerja);
        edtJamKerja = findViewById(R.id.edtJamKerja);
        edtHasilKerja = findViewById(R.id.edtHasilPekerjaan);
        btnKembaliPekerjaan = findViewById(R.id.btnKembaliPekerjaan);

        //set tanggal dan waktu sekarang
        String currentDate = date.format(new Date());
        edtTanggalKerja.setText(currentDate);

        String currentTime = time.format(new Date());
        edtJamKerja.setText(currentTime);

        //persiapa datepciker dan timepicker
        edtTanggalKerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
        
        edtJamKerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog();
            }
        });

        //generate spinner uraian tugas
        Spinner spinnerUraianTugas = findViewById(R.id.sprUraianTugas);

        //pakai rest server seharusnya - hardcode saja
        String[] items = new String[] {
                "Melaksanakan monitoring kegiatan pengelolaan data statistik (sektoral) & Integrasi System",
                "Melaksanakan koordinasi terkait sub bidang pengelolaan data statistik (sektoral) & Integrasi System",
                "Perumusan kebijakan di bidang pengelolaan data statistik dan integrasi sistem informasi",
                "Melaksanakan tugas administrasi Sub Bidang Integrasi System dan Pengelolaan data statistik",
                "Melakukan Konsultasi dengan Atasan",
                "Melaksanakan penyusunan program kerja Integrasi Sistem Informasi; & penyusunan program kerja Pengelolaan Data Statistik",
                "Melaksanakan tugas lain yang diberikan oleh Kepala Bidang dalam rangka kelancaran pelaksanaan tugas Bidang Pengelolaan Data Statistik dan Layanan E-Government",
                "Melaksanakan pelaporan dan evaluasi kegiatan",
                "Memberikan arahan staf Integrasi system & pengelolaan data statistik",
                "Melaksanakan penyusunan bahan telaahan staf sebagai bahan pertimbangan pengambilan kebijakan",
                "Melaksanakan pengelolaan data melalui aplikasi perangkat lunak dan sistem informasi yang terintegrasi di bidang data statistik",
                "Melaksanakan Implementasi teknis integrasi sistem informasi dan perangkat pendukung dengan lembaga /daerah /instansi lain"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        spinnerUraianTugas.setAdapter(adapter);

        btnKembaliPekerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showTimeDialog() {

        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                edtJamKerja.setText(hourOfDay+":"+minute);
            }
        },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    private void showDateDialog() {

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                edtTanggalKerja.setText(date.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
