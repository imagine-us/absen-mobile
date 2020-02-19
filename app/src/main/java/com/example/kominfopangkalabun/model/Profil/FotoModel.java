package com.example.kominfopangkalabun.model.Profil;

import com.google.gson.annotations.SerializedName;

public class FotoModel {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("nama_file")
    private String nama_file;

    public FotoModel(String status, String message, String nama_file) {
        this.status = status;
        this.message = message;
        this.nama_file = nama_file;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNama_file() {
        return nama_file;
    }

    public void setNama_file(String nama_file) {
        this.nama_file = nama_file;
    }
}
