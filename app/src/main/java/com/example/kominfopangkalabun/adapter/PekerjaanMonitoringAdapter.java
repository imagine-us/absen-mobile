package com.example.kominfopangkalabun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kominfopangkalabun.R;
import com.example.kominfopangkalabun.model.Pekerjaan.Pekerjaan;

import java.util.List;

public class PekerjaanMonitoringAdapter extends RecyclerView.Adapter<PekerjaanMonitoringAdapter.MyViewHolder> {

    List<Pekerjaan> pekerjaanList;

    public PekerjaanMonitoringAdapter(List<Pekerjaan> pekerjaanList) {
        this.pekerjaanList = pekerjaanList;
    }

    @NonNull
    @Override
    public PekerjaanMonitoringAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View menuView = layoutInflater.inflate
                (R.layout.card_pekerjaan,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(menuView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PekerjaanMonitoringAdapter.MyViewHolder holder, int position) {
        final Pekerjaan pekerjaan = pekerjaanList.get(position);

        holder.nama.setText(pekerjaan.getPekerjaanNama());
        holder.tanggal.setText("Tanggal : " + pekerjaan.getPekerjaanTanggal());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Detail Pekerjaan",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pekerjaanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama, tanggal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.pekerjaanNama);
            tanggal = itemView.findViewById(R.id.pekerjaanTanggal);
        }
    }
}