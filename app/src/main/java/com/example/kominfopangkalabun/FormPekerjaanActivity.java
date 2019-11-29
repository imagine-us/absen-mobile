package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FormPekerjaanActivity extends AppCompatActivity {

    EditText edtTanggalKerja, edtJamKerja, edtDurasiKerja, edtJudulKerja, edtDetailKerja;
    Button simpan;
    Spinner uraian, subUraian;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
    SimpleDateFormat time = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pekerjaan);

        uraian = findViewById(R.id.sprUraianTugas);
        subUraian = findViewById(R.id.sprSubUraianTugas);
        edtTanggalKerja= findViewById(R.id.edtTanggalKerja);
        edtJamKerja = findViewById(R.id.edtJam);
        edtDurasiKerja = findViewById(R.id.edtDurasi);
        edtJudulKerja = findViewById(R.id.edtNamaPekerjaan);
        edtDetailKerja = findViewById(R.id.edtDetailPekerjaan);
        simpan = findViewById(R.id.btnSubmitKerja);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listUraian());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uraian.setAdapter(adapter);

//        uraian.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(FormPekerjaanActivity.this, android.R.layout.simple_spinner_item, listSubUraian(i));
//                subUraian.setAdapter(adapter2);
//            }
//        });

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

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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


    private String[] listUraian(){
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
       return items;
    }

    private String[] listSubUraian(int index){


        if(index==1) {
            String[] items = new String[]{"",""};
            return items;
        }
        else if(index==2) {
            String[] items = new String[]{"",""};
            return items;
        }
        else if(index==3) {
            String[] items = new String[]{"",""};
            return items;
        }
        else if(index==4) {
            String[] items = new String[]{"",""};
            return items;
        }
        else if(index==5) {
            String[] items = new String[]{"",""};
            return items;
        }
        else if(index==6) {
            String[] items = new String[]{"",""};
            return items;
        }
        else if(index==7) {
            String[] items = new String[]{"",""};
            return items;
        }
        else if(index==8) {
            String[] items = new String[]{"",""};
            return items;
        }
        else if(index==9) {
            String[] items = new String[]{"",""};
            return items;
        }
        else if(index==10) {
            String[] items = new String[]{"",""};
            return items;
        }
        else if(index==11) {
            String[] items = new String[]{"",""};
            return items;
        }
        else if(index==12) {
            String[] items = new String[]{"",""};
            return items;
        }
        else{
            String[] items = new String[]{""};
            return items;
        }
    }

}
