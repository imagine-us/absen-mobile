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

import com.example.kominfopangkalabun.DetailPekerjaanActivity;
import com.example.kominfopangkalabun.R;
import com.example.kominfopangkalabun.model.Pekerjaan.Pekerjaan;

import java.util.List;

public class PekerjaanAdapter extends RecyclerView.Adapter<PekerjaanAdapter.MyViewHolder> {

    List<Pekerjaan> pekerjaanList;

    public PekerjaanAdapter(List<Pekerjaan> pekerjaanList) {
        this.pekerjaanList = pekerjaanList;
    }

    @NonNull
    @Override
    public PekerjaanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View menuView = layoutInflater.inflate
                (R.layout.card_pekerjaan,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(menuView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PekerjaanAdapter.MyViewHolder holder, int position) {
        final Pekerjaan pekerjaan = pekerjaanList.get(position);

        holder.nama.setText(pekerjaan.getPekerjaanNama());
        String[] tanggal1 = pekerjaan.getPekerjaanTanggal().split(" ");
        String[] tanggal2 = tanggal1[0].split("-");
        holder.tanggal.setText("Tanggal : " + tanggal2[2] + "-" + tanggal2[1] + "-" + tanggal2[0]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(),pekerjaan.getId(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), DetailPekerjaanActivity.class);
                intent.putExtra("idpekerjaan",pekerjaan.getPekerjaanId());
                view.getContext().startActivity(intent);
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
