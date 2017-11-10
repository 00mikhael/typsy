package com.example.typsy.data.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

/**
 * Created by gravity on 11/3/17.
 */
@Entity(tableName = "price", primaryKeys = {"f_code", "t_code"},
        indices = {@Index(value = {"f_code", "t_code"}, unique = true)})
public class Price {
    @NonNull
    @ColumnInfo(name = "f_code")
    public String fCode;

    @NonNull
    @ColumnInfo(name = "t_code")
    public String tCode;

    @ColumnInfo(name = "f_sym")
    public String fSym;

    @ColumnInfo(name = "t_sym")
    public String tSym;

    @ColumnInfo(name = "d_price")
    public String displayPrice;

    @ColumnInfo(name = "r_price")
    public double rawPrice;

    public String getfSym() {
        return fSym;
    }

    public String gettSym() {
        return tSym;
    }

    public String getfCode() {
        return fCode;
    }

    public String gettCode() {
        return tCode;
    }

    public String getDisplayPrice() {
        return displayPrice;
    }

    public double getRawPrice() {
        return rawPrice;
    }

    public void setfSym(String fSym) {
        this.fSym = fSym;
    }

    public void settSym(String tSym) {
        this.tSym = tSym;
    }

    public void setfCode(@NonNull String fCode) {
        this.fCode = fCode;
    }

    public void settCode(@NonNull String tCode) {
        this.tCode = tCode;
    }

    public void setDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
    }

    public void setRawPrice(double rawPrice) {
        this.rawPrice = rawPrice;
    }

    @Ignore
    public Price() {}

    public Price(@NonNull String fCode,@NonNull String tCode, String fSym, String tSym, String displayPrice, double rawPrice) {

        this.fCode = fCode;
        this.tCode = tCode;
        this.fSym = fSym;
        this.tSym = tSym;
        this.displayPrice = displayPrice;
        this.rawPrice = rawPrice;
    }
}
