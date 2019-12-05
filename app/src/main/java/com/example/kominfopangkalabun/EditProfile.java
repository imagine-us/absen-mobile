package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class EditProfile extends AppCompatActivity {

    TextView namaprofile, nipprofile, passprofile;
    ImageView fotoprofil, gantifotoprofil;
    Button btngantiprofil, back;
    private SharedPreferences sp;
    String idprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        this.sp = this.getSharedPreferences("sp", Context.MODE_PRIVATE);

        namaprofile = findViewById(R.id.namaEditProfil);
        nipprofile = findViewById(R.id.nipEditProfil);

        fotoprofil = findViewById(R.id.imageEditProfil);
        gantifotoprofil = findViewById(R.id.tombolGantiProfil);
        btngantiprofil = findViewById(R.id.saveGantiPass);
        back = findViewById(R.id.btnBackEditProfile);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        idprofile = this.sp.getString("key_id",null);
        namaprofile.setText(this.sp.getString("key_nama",null));
        nipprofile.setText(this.sp.getString("key_nip",null));
        passprofile.setText(this.sp.getString("key_password",null));
        Picasso.with(this).load(this.sp.getString("key_foto",null)).placeholder(R.drawable.icon_profile).transform(new PicassoCircleTransformation()).into(fotoprofil);
    }
}
