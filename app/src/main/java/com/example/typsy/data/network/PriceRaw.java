package com.example.typsy.data.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 10/20/17.
 */
// Class for raw version of the api response
public class PriceRaw {
    @SerializedName("FROMSYMBOL")
    private String fsym;
    @SerializedName("TOSYMBOL")
    private String tsym;
    @SerializedName("PRICE")
    private double price;

    public String getFsym() {
        return fsym;
    }

    public String getTsym() {
        return tsym;
    }

    public double getPrice() {
        return price;
    }

    public PriceRaw(String fsym, String tsym, double price) {
        this.fsym = fsym;
        this.tsym = tsym;
        this.price = price;
    }
}
