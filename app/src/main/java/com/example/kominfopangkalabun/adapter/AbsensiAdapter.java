package com.example.kominfopangkalabun.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kominfopangkalabun.DetailAbsenActivity;
import com.example.kominfopangkalabun.R;
import com.example.kominfopangkalabun.model.Absensi.Absensi;

import java.util.List;

public class AbsensiAdapter extends RecyclerView.Adapter<AbsensiAdapter.MyViewHolder> {

    List<Absensi> absensiList;

    public AbsensiAdapter(List<Absensi> absensiList) {
        this.absensiList = absensiList;
    }

    @NonNull
    @Override
    public AbsensiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View menuView = layoutInflater.inflate
                (R.layout.card_presensi,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(menuView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AbsensiAdapter.MyViewHolder holder, int position) {
        final Absensi absensi = absensiList.get(position);

        holder.dateTime.setText(absensi.getTanggal()+"  "+absensi.getJam());
//        holder.lokasi.setText(absensi.getLokasi());
        holder.longLat.setText("Long:"+absensi.getLongitude()+" Lat:"+absensi.getLatitude());

        String keterangan = "";
        if(absensi.getKeterangan().equals("D")){
            keterangan = "Datang";
        }
        if(absensi.getKeterangan().equals("P")){
            keterangan = "Pulang";
        }
        holder.keterangan.setText(keterangan);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailAbsenActivity.class);
                String[] detailAbsensiString = new String[] {absensi.getTanggal(), absensi.getJam(),absensi.getLongitude(),absensi.getLatitude(), absensi.getKeterangan()};
                intent.putExtra("Detail", detailAbsensiString);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return absensiList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dateTime, lokasi, longLat, keterangan;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTime = itemView.findViewById(R.id.dateTimePresensi);
            keterangan = itemView.findViewById(R.id.keteranganPresensi);
            //lokasi = itemView.findViewById(R.id.lokasiPresensi);
            longLat = itemView.findViewById(R.id.longlatPresensi);
        }
    }
}
