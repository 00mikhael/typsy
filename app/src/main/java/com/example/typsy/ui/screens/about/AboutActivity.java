package com.example.typsy.ui.screens.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import com.example.typsy.AppConstant;
import com.example.typsy.R;
import com.example.typsy.data.local.entity.Rating;

/**
 * Created by gravity on 10/29/17.
 */

public class AboutActivity extends AppCompatActivity implements RatingDialog.RateListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        FloatingActionButton mRateFab = findViewById(R.id.rating_fab);
        mRateFab.setOnClickListener(view -> showRatingDialog());
    }

    // on rate clicked on dialog - creates new rating, calls sendmail method
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, float rating, String feedback) {
        Rating r = new Rating();
        r.setFeedback(feedback);
        r.setRating(rating);
        sendMail(r);
    }

    // shows rating dialog on fab clicked
    public void showRatingDialog() {
        DialogFragment dialog = new RatingDialog();
        dialog.show(getSupportFragmentManager(), getString(R.string.rating_dialog));
    }

    // sends mail
    public void sendMail(Rating rating) {
        String body = "\n" + "Feedback : " + rating.getFeedback() + "\n" + "Rating : " + rating.getRating();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, AppConstant.Mail);
        intent.putExtra(Intent.EXTRA_SUBJECT, AppConstant.SUBJECT);
        intent.putExtra((Intent.EXTRA_TEXT), body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
