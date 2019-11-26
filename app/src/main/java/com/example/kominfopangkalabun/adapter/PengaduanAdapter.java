package com.example.kominfopangkalabun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kominfopangkalabun.R;
import com.example.kominfopangkalabun.model.Pengaduan.Pengaduan;

import java.util.List;

public class PengaduanAdapter extends RecyclerView.Adapter<PengaduanAdapter.MyViewHolder> {

    List<Pengaduan> pengaduanList;

    public PengaduanAdapter(List<Pengaduan> pengaduanList) {
        this.pengaduanList = pengaduanList;
    }

    @NonNull
    @Override
    public PengaduanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View menuView = layoutInflater.inflate
                (R.layout.card_pengaduan,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(menuView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Pengaduan pengaduan = pengaduanList.get(position);

        String nomorstatus = pengaduan.getPengaduanStatus();

        holder.subjek.setText("Subjek");

        if(nomorstatus == "0"){
            holder.status.setText("Belum Proses");
        }
        else if(nomorstatus == "1"){
            holder.status.setText("Sudah Proses");
        }
        else if(nomorstatus == "2"){
            holder.status.setText("Dalam Proses");
        }
        else{
            holder.status.setText("Error");
        }

        holder.tanggal.setText(pengaduan.getPengaduanTanggalPengaduan());
    }

    @Override
    public int getItemCount() {
        return pengaduanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subjek, status, tanggal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            subjek = itemView.findViewById(R.id.pengaduanSubjek);
            status = itemView.findViewById(R.id.pengaduanStatus);
            tanggal = itemView.findViewById(R.id.pengaduanTanggal);
        }
    }
}
