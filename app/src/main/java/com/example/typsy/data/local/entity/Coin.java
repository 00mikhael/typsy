package com.example.typsy.data.local.entity;

import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

/**
 * Created by gravity on 10/18/17.
 */

public class Coin {
    public String code;

    public int imageUrl;

    public String coinName;


    public int getImageUrl() {
        return imageUrl;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    public String getCoinName() {
        return coinName;
    }

    @Ignore
    public Coin() {}

    public Coin(String code, int imageUrl, String coinName) {
        this.code = code;
        this.imageUrl = imageUrl;
        this.coinName = coinName;
    }
}
