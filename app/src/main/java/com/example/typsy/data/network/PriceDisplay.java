package com.example.typsy.data.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 10/20/17.
 */

public class PriceDisplay {
    @SerializedName("FROMSYMBOL")
    private String fsym;
    @SerializedName("TOSYMBOL")
    private String tsym;
    @SerializedName("PRICE")
    private String price;

    public String getFsym() {
        return fsym;
    }

    public String getTsym() {
        return tsym;
    }

    public String getPrice() {
        return price;
    }

    public PriceDisplay(String fsym, String tsym, String price) {
        this.fsym = fsym;
        this.tsym = tsym;
        this.price = price;
    }
}
