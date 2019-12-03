package com.example.kominfopangkalabun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kominfopangkalabun.R;
import com.example.kominfopangkalabun.model.Agenda.Agenda;

import java.util.List;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.MyViewHolder> {

    List<Agenda> agendaList;

    public AgendaAdapter(List<Agenda> agendaList) {
        this.agendaList = agendaList;
    }

    @NonNull
    @Override
    public AgendaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View menuView = layoutInflater.inflate
                (R.layout.card_agenda,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(menuView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Agenda agenda = agendaList.get(position);



        holder.subjek.setText(agenda.getIsiagenda());
/*

*/
        holder.tanggal.setText(agenda.getTanggal());

    }

    @Override
    public int getItemCount() {
        return agendaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subjek, tanggal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            subjek = itemView.findViewById(R.id.agendaIsi);
            // status = itemView.findViewById(R.id.agendaStatus);
            tanggal = itemView.findViewById(R.id.agendaTanggal);
        }
    }
}
