package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kominfopangkalabun.model.Agenda.AgendaModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAgendaActivity extends AppCompatActivity {

    EditText isiAgenda, tanggalAgenda;
    Button simpanAgenda, kembaliAgenda;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private SharedPreferences sp;
    BaseApiService mApiService;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter, dateFormatDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_agenda);

        isiAgenda = findViewById(R.id.edtAgenda);
        tanggalAgenda = findViewById(R.id.edtTanggalAgenda);
        simpanAgenda = findViewById(R.id.btnFormAgendaKirim);
        kembaliAgenda = findViewById(R.id.btnFormAgendaKembali);
        this.sp = this.getSharedPreferences("sp", Context.MODE_PRIVATE);
        mApiService = UtilsApi.getAPIService();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        dateFormatDB = new SimpleDateFormat("yyyy-MM-dd");

        kembaliAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tanggalAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        simpanAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tanggalsekarang = tanggalAgenda.getText().toString();
                String isiagenda = isiAgenda.getText().toString();
                String pnsid = sp.getString("key_id",null);
                
                Date d = null;

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    d = sdf.parse(tanggalsekarang);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String tanggalbaru = dateFormatDB.format(d);

                Call<AgendaModel> call = mApiService.insertagenda(pnsid,isiagenda,tanggalbaru);
                call.enqueue(new Callback<AgendaModel>() {
                    @Override
                    public void onResponse(Call<AgendaModel> call, Response<AgendaModel> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Membuat Agenda Berhasil",Toast.LENGTH_LONG);
                            Intent intent = new Intent(getApplicationContext(),AgendaActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Mohon maaf ada masalah.",Toast.LENGTH_LONG);
                        }
                    }

                    @Override
                    public void onFailure(Call<AgendaModel> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String tanggalsekarang = df.format(c);
        tanggalAgenda.setText(tanggalsekarang);
    }

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tanggalAgenda.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}
