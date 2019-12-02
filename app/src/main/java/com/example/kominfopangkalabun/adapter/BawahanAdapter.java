package com.example.kominfopangkalabun.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kominfopangkalabun.DetailAbsenActivity;
import com.example.kominfopangkalabun.MonitoringActivity;
import com.example.kominfopangkalabun.PicassoCircleTransformation;
import com.example.kominfopangkalabun.R;
import com.example.kominfopangkalabun.model.Absensi.Absensi;
import com.example.kominfopangkalabun.model.Monitoring.Bawahan;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BawahanAdapter extends RecyclerView.Adapter<BawahanAdapter.MyViewHolder> {

    List<Bawahan> bawahanList;

    Context context;
    public BawahanAdapter(List<Bawahan> bawahanList) {
        this.bawahanList = bawahanList;
    }

    @NonNull
    @Override
    public BawahanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View menuView = layoutInflater.inflate
                (R.layout.card_bawahan,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(menuView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BawahanAdapter.MyViewHolder holder, int position) {
        final Bawahan bawahan = bawahanList.get(position);

        holder.nama.setText(bawahan.getNama());
        holder.nip.setText(bawahan.getNip());
//        holder.lokasi.setText(absensi.getLokasi());
       // holder.foto.setImageDrawable(R.drawable.ic_launcher_foreground);
        if((bawahan.getFoto()!=null)&&(!bawahan.getFoto().isEmpty())) {
            Picasso.with(context).load(bawahan.getFoto()).placeholder(R.drawable.icon_profile).transform(new PicassoCircleTransformation()).into(holder.foto);
        }
        else{
            holder.foto.setImageResource(R.drawable.icon_profile);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MonitoringActivity.class);
                String[] detailAbsensiString = new String[] {bawahan.getId(),bawahan.getNip()};
                //intent.putExtra("Detail", detailAbsensiString);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bawahanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama, nip;
        ImageView foto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nip = itemView.findViewById(R.id.nipBawahan);
            nama = itemView.findViewById(R.id.namaBawahan);
            foto = itemView.findViewById(R.id.fotoBawahan);
        }
    }
}
