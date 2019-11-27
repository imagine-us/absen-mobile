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

import com.example.kominfopangkalabun.R;
import com.example.kominfopangkalabun.model.Pengaduan.Pengaduan;
import com.example.kominfopangkalabun.PengaduanTanggapan;

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

        final Pengaduan pengaduan = pengaduanList.get(position);

        String nomorstatus = pengaduan.getPengaduanStatus();

        holder.subjek.setText("Subjek");

        //kurang warna tiap status
        if(nomorstatus.equals("0")){
            holder.status.setText("Belum Diproses");
        }
        else if(nomorstatus.equals("1")){
            holder.status.setText("Sudah Diproses");
        }
        else if(nomorstatus.equals("2")){
            holder.status.setText("Masih Diproses");
        }
        else{
            holder.status.setText("Error");
        }

        holder.tanggal.setText(pengaduan.getPengaduanTanggalPengaduan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pengaduan.getPengaduanStatus().equals("1")){
                    Intent intent = new Intent(view.getContext(), PengaduanTanggapan.class);
                    intent.putExtra("Id", pengaduan.getPengaduanId());
                    view.getContext().startActivity(intent);
                }
                else if(pengaduan.getPengaduanStatus().equals("2")){
                    Toast.makeText(view.getContext(),"Pengaduan Masih Diproses",Toast.LENGTH_SHORT).show();
                }
                else if(pengaduan.getPengaduanStatus().equals("0")){
                    Toast.makeText(view.getContext(),"Pengaduan Belum Diproses",Toast.LENGTH_SHORT).show();
                }

            }
        });
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
