package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kominfopangkalabun.model.Login.Login;
import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsnm, edtPwd;
    Button btnLogin;

    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    // SharedPreferences yang akan digunakan untuk menulis dan membaca data
    private SharedPreferences sharedPrefs;

    // Key-key untuk data yang disimpan di SharedPrefernces
    private static final String NIP_KEY = "key_nip";
    private static final String ID_KEY = "key_id";
    private static final String NAMA_KEY = "key_nama";
    private static final String FOTO_KEY = "key_foto";
    private static final String PASSWORD_KEY = "key_password";
    private static final String KEEP_LOGIN_KEY = "key_keep_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsnm = findViewById(R.id.edtTxtUsername);
        edtPwd = findViewById(R.id.edtTxtPassword);
        btnLogin=findViewById(R.id.btnLogin);

        mContext = this;
        mApiService = UtilsApi.getAPIService();
        this.sharedPrefs = this.getSharedPreferences("sp", Context.MODE_PRIVATE);

        String key=this.sharedPrefs.getString(NAMA_KEY,null);

  //  Boolean key =this.sharedPrefs.getString("key_keep_login",null);
  // Toast.makeText(this,""+key,Toast.LENGTH_LONG).show();
      if(key!=null){
            Intent intent = new Intent(mContext, MainActivity.class);
          startActivity(intent);
      }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //loading = ProgressDialog.show(mContext, null, "Please Wait...", true, false);
                requestLogin();
                //Intent intent = new Intent(mContext, AbsensiActivity.class);
//                Intent intent = new Intent(mContext, MainActivity.class);
//                startActivity(intent);
            }
        });

    }

//

    private void requestLogin(){
        Call<Login> call =mApiService.tryLogin(edtUsnm.getText().toString(), edtPwd.getText().toString());
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                    int codeResponse = response.code();
                    if(codeResponse == 200){
                        //shared preferences disini
                        Toast.makeText(mContext,"Login Berhasil",Toast.LENGTH_SHORT).show();
                        Login login = response.body();
                        Intent intent = new Intent(mContext, MainActivity.class);
//                        intent.putExtra("nip", login.getNip());
//                        intent.putExtra("nama", login.getNama());

                        saveUsername(login.getNip(),login.getId(), login.getNama(),login.getPassword(),login.getFoto(),true);
                        startActivity(intent);
                    }
                    else if(codeResponse == 404){
                        Toast.makeText(mContext,"User tidak ditemukan",Toast.LENGTH_SHORT).show();
                    }
                    else if(codeResponse == 400){
                        Toast.makeText(mContext,"Password Salah",Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
//                        loading.dismiss();
            }
        });
    }

    private void saveUsername(String key,String id, String nama, String password, String foto, Boolean save)
    {

        SharedPreferences.Editor editor = this.sharedPrefs.edit();

        if(save){
            editor.putString(NIP_KEY, key);
            editor.putString(ID_KEY,id);
            editor.putString(NAMA_KEY, nama);
            editor.putString(PASSWORD_KEY, password);
            editor.putString(FOTO_KEY, foto);
            editor.putString(KEEP_LOGIN_KEY,"true");
        }
        else {
            editor.remove(NIP_KEY);
            editor.remove(ID_KEY);
            editor.remove(NAMA_KEY);
            editor.remove(PASSWORD_KEY);
            editor.remove(FOTO_KEY);
            editor.remove(KEEP_LOGIN_KEY);
        }
        editor.apply();
    }

    public void onBackPressed() {
        finish();
        System.exit(0);
    }

}
