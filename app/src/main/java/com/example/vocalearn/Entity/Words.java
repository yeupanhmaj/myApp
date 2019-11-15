package com.example.vocalearn.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "Words_table")
public class Words {
    @PrimaryKey()@NonNull
    private String Tu;

    private String PhatAm ;

    private String Nghia ;

    private String Ghichu ;

    private String Status ;

    public Words(String tu, String phatAm, String nghia, String ghichu, String status) {
        Tu = tu;
        PhatAm = phatAm;
        Nghia = nghia;
        Ghichu = ghichu;
        Status = status;
    }

    public Words() {
    }

    public String getTu() {
        return Tu;
    }

    public void setTu(String tu) {
        Tu = tu;
    }

    public String getPhatAm() {
        return PhatAm;
    }

    public void setPhatAm(String phatAm) {
        PhatAm = phatAm;
    }

    public String getNghia() {
        return Nghia;
    }

    public void setNghia(String nghia) {
        Nghia = nghia;
    }

    public String getGhichu() {
        return Ghichu;
    }

    public void setGhichu(String ghichu) {
        Ghichu = ghichu;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
