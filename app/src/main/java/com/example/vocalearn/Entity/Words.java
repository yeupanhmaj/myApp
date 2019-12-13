package com.example.vocalearn.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "Words_table")
public class Words implements Parcelable {
    @PrimaryKey()@NonNull
    private String Tu;

    private String PhatAm ;

    private String Nghia ;

    private String Ghichu ,Chude;

    private int Hard ,Favorite,Learned;

    public Words(@NonNull String tu, String phatAm, String nghia, String ghichu, String chude, int hard, int favorite, int learned) {
        Tu = tu;
        PhatAm = phatAm;
        Nghia = nghia;
        Ghichu = ghichu;
        Chude = chude;
        Hard = hard;
        Favorite = favorite;
        Learned = learned;
    }

    public Words() {
    }

    protected Words(Parcel in) {
        Tu = in.readString();
        PhatAm = in.readString();
        Nghia = in.readString();
        Ghichu = in.readString();
        Hard = in.readInt();
        Favorite = in.readInt();
        Learned = in.readInt();
    }


    public static final Creator<Words> CREATOR = new Creator<Words>() {
        @Override
        public Words createFromParcel(Parcel in) {
            return new Words(in);
        }

        @Override
        public Words[] newArray(int size) {
            return new Words[size];
        }
    };

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

    public int getHard() {
        return Hard;
    }

    public void setHard(int hard) {
        Hard = hard;
    }

    public int getFavorite() {
        return Favorite;
    }

    public void setFavorite(int favorite) {
        Favorite = favorite;
    }

    public int getLearned() {
        return Learned;
    }

    public void setLearned(int learned) {
        Learned = learned;
    }

    public String getChude() {
        return Chude;
    }

    public void setChude(String chude) {
        Chude = chude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Tu);
        dest.writeString(PhatAm);
        dest.writeString(Nghia);
        dest.writeString(Ghichu);
        dest.writeString(Chude);
        dest.writeInt(Hard);
        dest.writeInt(Favorite);
        dest.writeInt(Learned);
    }
}
