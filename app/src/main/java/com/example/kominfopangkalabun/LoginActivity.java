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

//    private void requestLoginLama(){
//        mApiService.loginRequest(edtUsnm.getText().toString(), edtPwd.getText().toString())
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
////                            Toast.makeText(mContext,"response", Toast.LENGTH_LONG).show();
////                            loading.dismiss();
////                            try {
////                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
////                                if (jsonRESULTS.getString("error").equals("false")){
////                                    // Jika login berhasil maka data username yang ada di response API
////                                    // akan diparsing ke activity selanjutnya.
////                                    Toast.makeText(mContext,"ikii  "+response.body().string(),Toast.LENGTH_LONG).show();
////                                   // String username = jsonRESULTS.getJSONObject("user").getString("username");
////                                    String username = edtUsnm.getText().toString();
////                                    String nama= jsonRESULTS.getString("nama");
////                                    String id = jsonRESULTS.getString("id");
////
////                                    Intent intent = new Intent(mContext, MainActivity.class);
////                                    intent.putExtra("nip", username);
////                                    intent.putExtra("id",id);
////                                    intent.putExtra("nama",nama);
////                                   // Toast.makeText(mContext, nama+" BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
////                                    startActivity(intent);
////                                } else {
////                                    // Jika login gagal
////                                    String error_message = jsonRESULTS.getString("error_msg");
////                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
////                                }
////                            } catch (JSONException e) {
////                                e.printStackTrace();
////                            } catch (IOException e) {
////                                e.printStackTrace();
////                            }
//
//                            mApiService.loginHasil(edtUsnm.getText().toString()).enqueue(new Callback<Login>() {
//                                @Override
//                                public void onResponse(Call<Login> call, Response<Login> response) {
//                                      String username = edtUsnm.getText().toString();
//                                    String nama= response.body().getNama();
//                                    String id = response.body().getId();
//
//                                    Intent intent = new Intent(mContext, MainActivity.class);
//                                    intent.putExtra("nip", username);
//                                    intent.putExtra("id",id);
//                                    intent.putExtra("nama",nama);
//                                   Toast.makeText(mContext, nama+" BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
//                                    startActivity(intent);
//                                }
//
//                                @Override
//                                public void onFailure(Call<Login> call, Throwable t) {
//
//                                }
//                            });
//
//                            loading.dismiss();
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.e("debug", "onFailure: ERROR > " + t.toString());
////                        loading.dismiss();
//                    }
//                });
//    }

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
                        intent.putExtra("nip", login.getNip());
                        intent.putExtra("nama", login.getNama());
                        intent.putExtra("id",login.getId());
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
}
