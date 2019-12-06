package com.example.kominfopangkalabun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kominfopangkalabun.retrofit.BaseApiService;
import com.example.kominfopangkalabun.retrofit.UtilsApi;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {

    BaseApiService mApiService;
    TextView namaprofile, nipprofile, passprofile;
    ImageView fotoprofil, gantifotoprofil;
    Button btngantiprofil, back;
    private SharedPreferences sp;
    String idprofile;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageAbsen;
    int flag = 0;
    Intent intent;
    Uri fileUri;
    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 800;
    public final int SELECT_FILE = 1;
    private Bitmap decoded;

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


        idprofile = this.sp.getString("key_id", null);
        namaprofile.setText(this.sp.getString("key_nama", null));
        nipprofile.setText(this.sp.getString("key_nip", null));
        Picasso.with(this).load(this.sp.getString("key_foto", null)).placeholder(R.drawable.icon_profile).transform(new PicassoCircleTransformation()).into(fotoprofil);

        gantifotoprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
                //dispatchTakePictureIntent();
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

        if (flag == 1) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                final Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageAbsen = imageBitmap;
                fotoprofil.setImageBitmap(imageAbsen);
                try {
                    sendImage();
                    Toast.makeText(EditProfile.this, "Upload Foto Profile berhasil", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(EditProfile.this, "Upload Foto Profile Gagal, Periksa Koneksi anda.", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (flag == 2) {
            Log.e("onActivityResult", "requestCode " + requestCode + ", resultCode " + resultCode);

            if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
                try {
                    // mengambil gambar dari Gallery
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(EditProfile.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {

        }
        flag = 0;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageAbsen = decoded;
        fotoprofil.setImageBitmap(decoded);
        try {
            sendImage();
            Toast.makeText(EditProfile.this, "Upload Foto Profile berhasil", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(EditProfile.this, "Upload Foto Profile Gagal, Periksa Koneksi anda.", Toast.LENGTH_SHORT).show();
        }

    }

    private void selectImage() {

        final CharSequence[] items = {"Ambil dari Kamera", "Ambil dari Gallery",
                "Keluar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);
        builder.setTitle("Ganti Foto Profile");
        builder.setIcon(R.drawable.logo);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Ambil dari Kamera")) {
                    flag = 1;
                    dispatchTakePictureIntent();
                } else if (items[item].equals("Ambil dari Gallery")) {
                    flag = 2;
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
                } else if (items[item].equals("Keluar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void sendImage() {

        //create a file to write bitmap data
        String filename = "imageProfil"+this.sp.getString("key_nip",null)+".JPG";
        File f = new File(EditProfile.this.getCacheDir(), filename);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mApiService = UtilsApi.getAPIService();
//Convert bitmap to byte array
        Bitmap bitmap = imageAbsen;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //-------------------------------
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), f);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", f.getName(), reqFile);

        Call<ResponseBody> req = mApiService.postImage(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Do Something with response
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //failure message
                t.printStackTrace();
            }
        });


    }


}
