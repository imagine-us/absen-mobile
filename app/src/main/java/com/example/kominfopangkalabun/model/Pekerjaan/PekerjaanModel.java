package com.example.kominfopangkalabun.model.Pekerjaan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PekerjaanModel {

    @SerializedName("status")
    private String pekerjaanModelStatus;

    @SerializedName("data")
    private List<Pekerjaan> pekerjaanModelList;

    public PekerjaanModel(String pekerjaanModelStatus, List<Pekerjaan> pekerjaanModelList) {
        this.pekerjaanModelStatus = pekerjaanModelStatus;
        this.pekerjaanModelList = pekerjaanModelList;
    }

    public String getPekerjaanModelStatus() {
        return pekerjaanModelStatus;
    }

    public void setPekerjaanModelStatus(String pekerjaanModelStatus) {
        this.pekerjaanModelStatus = pekerjaanModelStatus;
    }

    public List<Pekerjaan> getPekerjaanModelList() {
        return pekerjaanModelList;
    }

    public void setPekerjaanModelList(List<Pekerjaan> pekerjaanModelList) {
        this.pekerjaanModelList = pekerjaanModelList;
    }
}
