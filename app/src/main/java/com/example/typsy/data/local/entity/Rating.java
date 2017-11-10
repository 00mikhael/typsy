package com.example.typsy.data.local.entity;

import android.support.annotation.Nullable;

/**
 * Created by gravity on 11/9/17.
 */

public class Rating {
    @Nullable
    private String feedback;
    @Nullable
    private float rating;
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public float getRating() {
        return rating;
    }

    public Rating() {}

    public Rating(String feedback, float rating) {
        this.feedback = feedback;
        this.rating = rating;
    }
}
