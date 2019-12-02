package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kominfopangkalabun.model.Pekerjaan.Pekerjaan;
import com.example.kominfopangkalabun.model.Pekerjaan.PekerjaanModel;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPekerjaanActivity extends AppCompatActivity {

    EditText edtTanggalKerja, edtJamKerja, edtDurasiKerja, edtJudulKerja, edtDetailKerja;
    Button simpan, back;
    Spinner uraian, subUraian;
    BaseApiService mApiService;
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
        back = findViewById(R.id.btnBackPekerjaan);
        mApiService = UtilsApi.getAPIService();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, listUraian());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uraian.setAdapter(adapter);

//        uraian.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(FormPekerjaanActivity.this, android.R.layout.simple_spinner_item, listSubUraian(i));
//                subUraian.setAdapter(adapter2);
//            }
//        });

        uraian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(FormPekerjaanActivity.this, R.layout.spinner_item, listSubUraian(i));
                subUraian.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
            public void onClick(final View view) {
                String nip = "123";
                String texturaian = uraian.getSelectedItem().toString();
                String textanalisis = subUraian.getSelectedItem().toString();

                String tgl = edtTanggalKerja.getText().toString();
                String[] separated = tgl.split("\\.");
                String newtgl = separated[2] + "-" +separated[1] + "-" + separated[0];

                String waktmulai = edtJamKerja.getText().toString();
                String newwaktumulai = newtgl + " " + waktmulai + ":00";

                String durasi = edtDurasiKerja.getText().toString();
                String pekerjaan = edtJudulKerja.getText().toString();
                String hasil = edtDetailKerja.getText().toString();

                Log.e("NIP",nip);
                Log.e("Uraian",texturaian);
                Log.e("Analisis",textanalisis);
                Log.e("Tanggal",newtgl);
                Log.e("Waktu Mulai", newwaktumulai);
                Log.e("Durasi",durasi);
                Log.e("Pekerjaan",pekerjaan);
                Log.e("Hasil",hasil);

                Call<PekerjaanModel> call = mApiService.insertPekerjaan(nip,texturaian,textanalisis,newtgl,newwaktumulai,durasi,pekerjaan,hasil);
                call.enqueue(new Callback<PekerjaanModel>() {
                    @Override
                    public void onResponse(Call<PekerjaanModel> call, Response<PekerjaanModel> response) {
                        Toast.makeText(view.getContext(),"Pekerjaan Terkirim",Toast.LENGTH_LONG).show();
//                        finish();
                        Intent intent = new Intent(view.getContext(), PekerjaanBaruActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<PekerjaanModel> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
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
        if(index==0){
            String [] items = new String[]{"Melaksanakan monitoring kegiatan Integrasi system Informasi|Maksimal 60 Menit|Kegiatan"};
            return items;
        }
        else if(index==1) {
            String[] items = new String[]{
                    "Melaksanakan koordinasi dengan Instansi/Kantor atau SOPD terkiat kegiatan Integrasi system dan pengelolaan data statistik | Maksimal 60 Menit|Aktivitas",
                    "Mengikuti rapat Bidang Pengelolaan Data Statistik dan Layanan E-Goverment | Maksimal 60 Menit | AktivitasBarat | Maksimal 60 Menit | Aktivitas",
                    "Mengikuti rapat koordinasi antar Bidang di Dinas Kominfo, statistik & persandian Kab. Kotawaringin"};
            return items;
        }
        else if(index==2) {
            String[] items = new String[]{
                    "Menyusun nota pertimbangan terkait kegiatan integrasi system informasi | Maksimal 60 Menit|Dokumen",
                    "Menyusun nota pertimbangan terkait kegiatan integrasi system informasi | Maksimal 60 Menit | Dokumen",
                    "Menyusun telaah Staf terkait Integrasi System Informasi | Maksimal 60 Menit | Dokumen",
                    "Perumusan kebijakan di bidang pengelolaan data statistik dan integrasi sistem informasi | Maksimal 60 Menit | Aktivitas"
            };
            return items;
        }
        else if(index==3) {
            String[] items = new String[]{
                    "Mengkonsep Surat terkait sub bidang Integrasi system dan pengelolaan data statistik | Maksimal 30 Menit|Aktivitas",
                    "Mengkoreksi Surat Keluar terkait Sub bidang Integrasi system dan Pengelolaan data Statistik | Maksimal 15 Menit | Aktivitas"
            };
            return items;
        }
        else if(index==4) {
            String[] items = new String[]{
                    "Melakukan Konsultasi dengan Kepala Bidang Pengelolaan Data Statistik & Layanan E-Goverment | Maksimal 60 Menit|Kegiatan",
                    "Melakukan Konsultasi dengan Kepala Dinas Kominfo, Statistik & Persandian | Maksimal 60 Menit | Kegiatan"
            };
            return items;
        }
        else if(index==5) {
            String[] items = new String[]{"Melaksanakan penyusunan program kerja Seksi Pengelolaan Data Statistik dan Integrasi Sistem Informasi; | Maksimal 60 Menit | Aktivitas"};
            return items;
        }
        else if(index==6) {
            String[] items = new String[]{
                    "Melaksanakan tugas lain yang diberikan oleh Kepala Bidang dalam rangka kelancaran pelaksanaan tugas Bidang Pengelolaan Data Statistik dan Layanan E-Government. | Maksimal 60 Menit | Aktivitas"
            };
            return items;
        }
        else if(index==7) {
            String[] items = new String[]{"Melaksanakan pelaporan dan evaluasi kegiatan; dan | Maksimal 60 Menit | Aktivitas"};
            return items;
        }
        else if(index==8) {
            String[] items = new String[]{"Memberikan arahan staf Integrasi system & pengelolaan data statistik | Maksimal 30 Menit | Aktivitas"};
            return items;
        }
        else if(index==9) {
            String[] items = new String[]{"Melaksanakan penyusunan bahan telaahan staf sebagai bahan pertimbangan pengambilan kebijakan; | Maksimal 60 Menit | Aktivitas"};
            return items;
        }
        else if(index==10) {
            String[] items = new String[]{"Melaksanakan pengelolaan data melalui aplikasi perangkat lunak dan sistem informasi yang terintegrasi di bidang data statistik; | Maksimal 60 Menit | Aktivitas"};
            return items;
        }
        else if(index==11) {
            String[] items = new String[]{
                    "Melakukan analisa masalah & Troubleshooting Integrasi system informasi | Maksimal 60 Menit|Aktivitas",
                    "Melakukan asistensi implementasi integrasi sistem informasi | Maksimal 60 Menit | Aktivitas",
                    "Melakukan monitoring Implementasi Perangkat dan Aplikasi Integrasi System | Maksimal 60 Menit | Aktivitas",
                    "Memberikan arahan staf untuk implementasi Integrasi system kepada staf | Maksimal 30 Menit | Kegiatan",
                    "Mengkoordinasikan kegiatan Integrasi System Informasi dengan lembaga/instansi/daerah lain yang terkait | Maksimal 60 Menit | Kegiatan",
                    "Menyusun perencanaan integrasi system Informasi | Maksimal 60 Menit | Dokumen"
            };
            return items;
        }
        else if(index==12) {
            String[] items = new String[]{
                    "Apel Pagi | Maksimal 10 Menit|Kegiatan",
                    "Apel pagi dilanjutkan pengarahan Bupati/Wakil Bupati/Sekda/Asisten/Staf Ahli/KA SKPD | Maksimal 45 Menit | Kegiatan",
                    "Apel pagi dilanjutkan senam/olahraga bersama | Maksimal 60 Menit | Kegiatan",
                    "Apel Sore | Maksimal 10 Menit | Kegiatan",
                    "Senam bersama dilanjutkan apel sore | Maksimal 30 Menit | Kegiatan",
                    "Upacara Hari Kesadaran Nasional | Maksimal 60 Menit | Kegiatan",
                    "Upacara Hari-hari Besar Nasional lainnya | Maksimal 60 Menit | Kegiatan"
            };
            return items;
        }
        else if(index==13) {
            String[] items = new String[]{
                    "Mengerjakan tugas lain | Maksimal 5 Menit  | Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 10 Menit | Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 15 Menit | Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 20 Menit | Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 30 Menit | Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 40 Menit | Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 50 Menit | Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 60 Menit | Kegiatan"
            };
            return items;
        }
        else if(index==14) {
            String[] items = new String[]{
                    "Membaca/mempelajari isi surat masuk | Maksimal 5 Menit|Surat",
                    "Membaca/mempelajari isi surat masuk dan mendisposisi surat (perhari eselon II) | Maksimal 60 Menit | Surat",
                    "Membaca/mempelajari isi surat masuk dan mendisposisi surat (perhari eselon III) | Maksimal 50 Menit | Surat",
                    "Membaca/mempelajari isi surat masuk dan mendisposisi surat (perhari eselon IV) | Maksimal 30 Menit | Surat",
                    "Mendisposisi surat | Maksimal 1 Menit | Surat"
            };
            return items;
        }
        else if(index==15) {
            String[] items = new String[]{
                    "Membaca/mempelajari isi naskah dinas dan menandatangani (Keputusan) | Maksimal 15 Menit|SK",
                    "Membaca/mempelajari isi naskah dinas dan menandatangani (non telaahan/kajian) | Maksimal 5 Menit | Surat",
                    "Membaca/mempelajari isi naskah dinas dan menandatangani (telaahan/kajian) | Maksimal 15 Menit | Surat"

            };
            return items;
        }
        else if(index==16) {
            String[] items = new String[]{
                    "Membaca/mempelajari naskah dinas dan memaraf (keputusan) | Maksimal 15 Menit|SK",
                    "Membaca/mempelajari naskah dinas dan memaraf (non telaahan/kajian/peraturan perundang-undangan) | Maksimal 2 Menit | Surat",
                    "Membaca/mempelajari naskah dinas dan memaraf (non telaahan/kajian/peraturan perundang-undangan) | Maksimal 60 Menit | 30 surat",
                    "Membaca/mempelajari naskah dinas dan memaraf (Perbup) | Maksimal 30 Menit | Perbup",
                    "Membaca/mempelajari naskah dinas dan memaraf (Perda) | Maksimal 45 Menit | Perda",
                    "Membaca/mempelajari naskah dinas dan memaraf (telaahan/kajian) | Maksimal 15 Menit | Surat"
            };
            return items;
        }
        else if(index==17) {
            String[] items = new String[]{
                    "Perjalanan dinas dalam daerah | Maksimal 60 Menit|Kegiatan",
                    "Perjalanan dinas ke luar daerah | Maksimal 60 Menit | Kegiatan"
            };
            return items;
        }
        else if(index==18) {
            String[] items = new String[]{
                    "Mengerjakan tugas lain | Maksimal 5 Menit|Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 10 Menit | Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 15 Menit | Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 20 Menit | Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 25 Menit | Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 30 Menit | Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 45 Menit | Kegiatan",
                    "Mengerjakan tugas lain | Maksimal 60 Menit | Kegiatan"
            };
            return items;
        }
        else if(index==19) {
            String[] items = new String[]{
                    "Olahraga | Maksimal 60 Menit | Kegiatan"
            };
            return items;
        }
        else if(index==20) {
            String[] items = new String[]{
                    "Kerja Bakti | Maksimal 60 Menit | Kegiatan"
            };
            return items;
        }
        else{
            String[] items = new String[]{""};
            return items;
        }
    }

}
