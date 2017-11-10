package com.example.typsy.data.network;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by gravity on 10/19/17.
 */
public class PriceResponse {
    @SerializedName("RAW")
    private Map<String, Map<String, PriceRaw>> raw;

    @SerializedName("DISPLAY")
    private Map<String, Map<String, PriceDisplay>> display;

    public Map<String, Map<String, PriceRaw>> getRaw() {
        return raw;
    }

    public Map<String, Map<String, PriceDisplay>> getDisplay() {
        return display;
    }

    public PriceResponse(Map<String, Map<String, PriceRaw>> raw,
                         Map<String, Map<String, PriceDisplay>> display) {
        this.raw = raw;
        this.display = display;
    }
}
