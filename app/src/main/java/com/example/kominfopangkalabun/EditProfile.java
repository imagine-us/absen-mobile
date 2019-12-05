package com.example.kominfopangkalabun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {

    TextView namaprofile, nipprofile, passprofile;
    ImageView fotoprofil, gantifotoprofil;
    Button btngantiprofil, back;
    private SharedPreferences sp;
    String idprofile;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageAbsen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        this.sp = this.getSharedPreferences("sp", Context.MODE_PRIVATE);

        namaprofile = findViewById(R.id.namaEditProfil);
        nipprofile = findViewById(R.id.nipEditProfil);
        fotoprofil = findViewById(R.id.imageEditProfil);
        gantifotoprofil = findViewById(R.id.tombolGantiProfil);

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
        Picasso.with(this).load(this.sp.getString("key_foto",null)).placeholder(R.drawable.icon_profile).transform(new PicassoCircleTransformation()).into(fotoprofil);

        gantifotoprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            final Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageAbsen=imageBitmap;

          fotoprofil.setImageBitmap(imageAbsen);

        }
    }

}
