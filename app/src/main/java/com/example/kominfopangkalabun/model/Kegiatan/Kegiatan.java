package com.example.kominfopangkalabun.model.Kegiatan;

import com.google.gson.annotations.SerializedName;

public class Kegiatan {

    @SerializedName("pekerjaan_id")
    private String pekerjaan_id;

    @SerializedName("rincian_id")
    private String rincian_id;

    @SerializedName("kegiatan_id")
    private String kegiatan_id;

    @SerializedName("nama_pekerjaan")
    private String nama_pekerjaan;

    @SerializedName("nama_rincian")
    private String nama_rincian;

    @SerializedName("nama_kegiatan")
    private String nama_kegiatan;

    @SerializedName("output")
    private String output_kegiatan;

    @SerializedName("tanggal")
    private String tanggal_kegiatan;

    @SerializedName("waktu_mulai")
    private String waktu_mulai_kegiatan;

    @SerializedName("waktu_akhir")
    private String waktu_akhir_kegiatan;

    public Kegiatan(String pekerjaan_id, String rincian_id, String kegiatan_id, String nama_pekerjaan, String nama_rincian, String nama_kegiatan, String output_kegiatan, String tanggal_kegiatan, String waktu_mulai_kegiatan, String waktu_akhir_kegiatan) {
        this.pekerjaan_id = pekerjaan_id;
        this.rincian_id = rincian_id;
        this.kegiatan_id = kegiatan_id;
        this.nama_pekerjaan = nama_pekerjaan;
        this.nama_rincian = nama_rincian;
        this.nama_kegiatan = nama_kegiatan;
        this.output_kegiatan = output_kegiatan;
        this.tanggal_kegiatan = tanggal_kegiatan;
        this.waktu_mulai_kegiatan = waktu_mulai_kegiatan;
        this.waktu_akhir_kegiatan = waktu_akhir_kegiatan;
    }

    public String getPekerjaan_id() {
        return pekerjaan_id;
    }

    public void setPekerjaan_id(String pekerjaan_id) {
        this.pekerjaan_id = pekerjaan_id;
    }

    public String getRincian_id() {
        return rincian_id;
    }

    public void setRincian_id(String rincian_id) {
        this.rincian_id = rincian_id;
    }

    public String getKegiatan_id() {
        return kegiatan_id;
    }

    public void setKegiatan_id(String kegiatan_id) {
        this.kegiatan_id = kegiatan_id;
    }

    public String getNama_pekerjaan() {
        return nama_pekerjaan;
    }

    public void setNama_pekerjaan(String nama_pekerjaan) {
        this.nama_pekerjaan = nama_pekerjaan;
    }

    public String getNama_rincian() {
        return nama_rincian;
    }

    public void setNama_rincian(String nama_rincian) {
        this.nama_rincian = nama_rincian;
    }

    public String getNama_kegiatan() {
        return nama_kegiatan;
    }

    public void setNama_kegiatan(String nama_kegiatan) {
        this.nama_kegiatan = nama_kegiatan;
    }

    public String getOutput_kegiatan() {
        return output_kegiatan;
    }

    public void setOutput_kegiatan(String output_kegiatan) {
        this.output_kegiatan = output_kegiatan;
    }

    public String getTanggal_kegiatan() {
        return tanggal_kegiatan;
    }

    public void setTanggal_kegiatan(String tanggal_kegiatan) {
        this.tanggal_kegiatan = tanggal_kegiatan;
    }

    public String getWaktu_mulai_kegiatan() {
        return waktu_mulai_kegiatan;
    }

    public void setWaktu_mulai_kegiatan(String waktu_mulai_kegiatan) {
        this.waktu_mulai_kegiatan = waktu_mulai_kegiatan;
    }

    public String getWaktu_akhir_kegiatan() {
        return waktu_akhir_kegiatan;
    }

    public void setWaktu_akhir_kegiatan(String waktu_akhir_kegiatan) {
        this.waktu_akhir_kegiatan = waktu_akhir_kegiatan;
    }
}
