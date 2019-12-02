package com.example.kominfopangkalabun.model.Monitoring;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BawahanModel {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<Bawahan> data;

    public BawahanModel(String status, List<Bawahan> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Bawahan> getData() {
        return data;
    }

    public void setData(List<Bawahan> data) {
        this.data = data;
    }
}
