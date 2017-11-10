package com.example.typsy.data.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by gravity on 10/18/17.
 */
@Entity(tableName = "conversion")
public class Conversion {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "from_sym")
    public String fromSym;

    @ColumnInfo(name = "to_sym")
    public String toSym;

    @ColumnInfo(name = "from_sym_code")
    public String fromCode;

    @ColumnInfo(name = "to_sym_code")
    public String toCode;

    @ColumnInfo(name = "from_avatar_url")
    public int fromAvatarUrl;

    @ColumnInfo(name = "to_avatar_url")
    public int toAvatarUrl;

    @ColumnInfo(name = "amount_to_convert")
    public double amountToConvert;

    @ColumnInfo(name = "conversion_rate")
    public double conversionRate;

    @ColumnInfo(name = "base_rate")
    public double baseRate;

    @ColumnInfo(name = "conversion_result")
    public double conversionResult;

    @ColumnInfo(name = "p_l")
    public double percentagePL;

    @ColumnInfo(name = "created_date")
    public String createdDate;

    @ColumnInfo(name = "created_text")
    public String createdText;

    public int getId() {
        return id;
    }

    public String getFromSym() {
        return fromSym;
    }

    public String getToSym() {
        return toSym;
    }

    public String getFromCode() {
        return fromCode;
    }

    public String getToCode() {
        return toCode;
    }

    public double getAmountToConvert() {
        return amountToConvert;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public double getConversionResult() {
        return amountToConvert * conversionRate;

    }

    public double getPercentagePL() {
        return percentagePL;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setFromSym(String fromSym) {
        this.fromSym = fromSym;
    }

    public void setToSym(String toSym) {
        this.toSym = toSym;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public void setAmountToConvert(double amountToConvert) {
        this.amountToConvert = amountToConvert;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public void setConversionResult(double conversionResult) {
        this.conversionResult = conversionResult;
    }

    public void setPercentagePL(double percentagePL) {
        this.percentagePL = percentagePL;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getFromAvatarUrl() {
        return fromAvatarUrl;
    }

    public void setFromAvatarUrl(int fromAvatarUrl) {
        this.fromAvatarUrl = fromAvatarUrl;
    }

    public int getToAvatarUrl() {
        return toAvatarUrl;
    }

    public void setToAvatarUrl(int toAvatarUrl) {
        this.toAvatarUrl = toAvatarUrl;
    }

    public String getCreatedText() {
        return createdText;
    }

    public void setCreatedText(String createdText) {
        this.createdText = createdText;
    }

    public double getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(double baseRate) {
        this.baseRate = baseRate;
    }

    @Ignore
    public Conversion() {
    }

    public Conversion(@NonNull int id, String fromSym, String toSym, String fromCode, String toCode,
                      int fromAvatarUrl, int toAvatarUrl, double amountToConvert, double conversionRate,
                      double baseRate, double conversionResult, double percentagePL, String createdDate,
                      String createdText) {
        this.id = id;
        this.fromSym = fromSym;
        this.toSym = toSym;
        this.fromCode = fromCode;
        this.toCode = toCode;
        this.fromAvatarUrl = fromAvatarUrl;
        this.toAvatarUrl = toAvatarUrl;
        this.amountToConvert = amountToConvert;
        this.conversionRate = conversionRate;
        this.baseRate = baseRate;
        this.conversionResult = conversionResult;
        this.percentagePL = percentagePL;
        this.createdDate = createdDate;
        this.createdText = createdText;
    }
}
