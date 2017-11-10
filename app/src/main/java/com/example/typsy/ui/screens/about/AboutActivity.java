package com.example.typsy.ui.screens.about;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import com.example.typsy.R;
import com.example.typsy.data.local.entity.Rating;

/**
 * Created by gravity on 10/29/17.
 */

public class AboutActivity extends AppCompatActivity implements RatingDialog.RateListener {

    private AboutViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        mViewModel = ViewModelProviders.of(this).get(AboutViewModel.class);
        CoordinatorLayout mCoordinatorLayout = findViewById(R.id.about_coordinator);
        FloatingActionButton mRateFab = findViewById(R.id.rating_fab);
        mRateFab.setOnClickListener(view -> showRatingDialog());

        mViewModel.getVmSnackMessage().observe(this, s -> {
            if (s != null) {
                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, s, Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, float rating, String feedback) {
        Rating r = new Rating();
        r.setFeedback(feedback);
        r.setRating(rating);
        mViewModel.sendMail(r);
    }

    public void showRatingDialog() {
        DialogFragment dialog = new RatingDialog();
        dialog.show(getSupportFragmentManager(), getString(R.string.rating_dialog));
    }
}
