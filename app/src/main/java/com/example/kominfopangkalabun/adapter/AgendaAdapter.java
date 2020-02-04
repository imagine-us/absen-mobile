package com.example.kominfopangkalabun.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kominfopangkalabun.DetailAgendaActivity;
import com.example.kominfopangkalabun.R;
import com.example.kominfopangkalabun.model.Agenda.Agenda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");

        Date d = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            d = sdf.parse(agenda.getTanggal());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String tanggalDB = dateFormater.format(d);

        holder.subjek.setText(agenda.getIsiagenda());
        holder.tanggal.setText(tanggalDB);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailAgendaActivity.class);
                intent.putExtra("idagenda",agenda.getId());
                view.getContext().startActivity(intent);
            }
        });
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
