package com.example.typsy.data.local.entity;

import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

/**
 * Created by gravity on 10/18/17.
 */

public class Currency {

    public String code;

    public int flag;

    public String name;

    public int getFlag() {
        return flag;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(@NonNull String code) {
        this.code = code;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Ignore
    public Currency() {}

    public Currency(String code, int flag, String name) {
        this.code = code;
        this.flag = flag;
        this.name = name;
    }
}
