package com.example.kominfopangkalabun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        Absensi absensi = absensiList.get(position);

        holder.dateTime.setText(absensi.getTanggal()+"  "+absensi.getJam());
//        holder.lokasi.setText(absensi.getLokasi());
        holder.longLat.setText("Long:"+absensi.getLongitude()+" Lat:"+absensi.getLatitude());
    }

    @Override
    public int getItemCount() {
        return absensiList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dateTime, lokasi, longLat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTime = itemView.findViewById(R.id.dateTimePresensi);
            //lokasi = itemView.findViewById(R.id.lokasiPresensi);
            longLat = itemView.findViewById(R.id.longlatPresensi);
        }
    }
}
