package com.example.kominfopangkalabun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kominfopangkalabun.adapter.BawahanAdapter;
import com.example.kominfopangkalabun.model.Monitoring.Bawahan;

import java.util.ArrayList;
import java.util.List;

public class BawahanActivity extends AppCompatActivity {

    Button back;
    RecyclerView rv;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bawahan);

        back = findViewById(R.id.btnBackMonitoringBawahan);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BawahanActivity.this, MainActivity.class));
            }
        });

        List<Bawahan> bawahanList = new ArrayList<>();

        Bawahan bawahan = new Bawahan("1","1","Namaku namamu");
        bawahanList.add(bawahan);

        rv = findViewById(R.id.rvBawahan);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        BawahanAdapter menuAdapter = new BawahanAdapter(bawahanList);
        rv.setAdapter(menuAdapter);
        rv.setLayoutManager(layoutManager);

    }
}
