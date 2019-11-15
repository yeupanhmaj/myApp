package com.example.vocalearn.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Chu_De")
public class ChuDe {
    @PrimaryKey(autoGenerate = true)
    private String IDChuDe;
    private String TenChuDe;

    public ChuDe() {
    }

    public ChuDe(String IDChuDe, String tenChuDe) {
        this.IDChuDe = IDChuDe;
        TenChuDe = tenChuDe;
    }

    public String getIDChuDe() {
        return IDChuDe;
    }

    public void setIDChuDe(String IDChuDe) {
        this.IDChuDe = IDChuDe;
    }

    public String getTenChuDe() {
        return TenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        TenChuDe = tenChuDe;
    }
}
