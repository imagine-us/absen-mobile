package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kominfopangkalabun.model.Login.GantiPass;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GantiPasswordActivity extends AppCompatActivity {

    Button back, simpan;
    EditText passLama, confirmPass, passBaru;
    private SharedPreferences sp;
    String pass, foto, nip, id;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_password);


        back = findViewById(R.id.btnBackEditPassword);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.sp = this.getSharedPreferences("sp", Context.MODE_PRIVATE);
        pass = this.sp.getString("key_password",null);
        foto = this.sp.getString("key_foto",null);
        id = this.sp.getString("key_id",null);
        nip = this.sp.getString("key_nip",null);
        passLama= findViewById(R.id.edtPassSkrg);
        passBaru= findViewById(R.id.edtPassBaru);
        confirmPass = findViewById(R.id.edtPassConfirm);
        mApiService = UtilsApi.getAPIService();

        simpan = findViewById(R.id.saveGantiPass);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((passBaru.getText().toString().equals(confirmPass.getText().toString()))&&(passLama.getText().toString().equals(pass) )){

                    Call<GantiPass> call =mApiService.editprofile(id,nip,passBaru.getText().toString(),foto);
                    call.enqueue(new Callback<GantiPass>() {
                        @Override
                        public void onResponse(Call<GantiPass> call, Response<GantiPass> response) {
                            int codeResponse = response.code();
                            if(codeResponse == 200){
                                //shared preferences disini
                               // Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                                GantiPass gp = response.body();
                                showDialogSukses();
                            }
                            else if(codeResponse == 404){
                                //Toast.makeText(getApplicationContext(),"User tidak ditemukan",Toast.LENGTH_SHORT).show();
                           showDialogBeda();
                            }
                            else if(codeResponse == 400){
                               // Toast.makeText(mContext,"Password Salah",Toast.LENGTH_SHORT).show();
                            showDialogBeda();
                            }

                        }

                        @Override
                        public void onFailure(Call<GantiPass> call, Throwable t) {
                            Log.e("debug", "onFailure: ERROR > " + t.toString());
//                        loading.dismiss();
                        }
                    });


                    showDialogSukses();

                }else{
                    showDialogBeda();
                }




            }
        });
    }

    private void showDialogBeda(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                GantiPasswordActivity.this);

        // set title dialog
        alertDialogBuilder.setTitle("Konfirmasi");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Password lama anda salah atau password baru yang anda masukkan berbeda")
                .setIcon(R.drawable.logo)
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();
        passLama.setText("");
        passBaru.setText("");
        confirmPass.setText("");
        // menampilkan alert dialog
        alertDialog.show();
    }

    private void showDialogSukses(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                GantiPasswordActivity.this);

        // set title dialog
        alertDialogBuilder.setTitle("Konfirmasi");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Password baru anda sudah tersimpan")
                .setIcon(R.drawable.logo)
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();
        passLama.setText("");
        passBaru.setText("");
        confirmPass.setText("");
        // menampilkan alert dialog
        alertDialog.show();
    }
}
