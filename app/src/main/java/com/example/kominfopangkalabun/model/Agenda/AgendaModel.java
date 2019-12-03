package com.example.kominfopangkalabun.model.Agenda;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AgendaModel {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<Agenda> listAgenda;

    public AgendaModel(String status, List<Agenda> listAgenda) {
        this.status = status;
        this.listAgenda = listAgenda;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Agenda> getListAgenda() {
        return listAgenda;
    }

    public void setListAgenda(List<Agenda> listAgenda) {
        this.listAgenda = listAgenda;
    }
}
