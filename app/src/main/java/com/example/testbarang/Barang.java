package com.example.testbarang;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Barang implements Serializable {
    private String kode;
    private String nama;

    public Barang(){

    }

    public String getKode(){
        return kode;
    }
    public void setKode(String kd){
        this.kode = kd;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }

    @NonNull
    @Override
    public String toString() {
        return " "+kode+"\n"+
                " "+nama;
    }
    public Barang(String kd, String nm){
        kode = kd;
        nama = nm;
    }
}
