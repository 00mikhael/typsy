package com.example.typsy.ui.screens.about;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.LayoutInflater;
import android.view.View;

import com.example.typsy.R;

/**
 * Created by gravity on 11/9/17.
 */

public class RatingDialog extends DialogFragment {

    public interface RateListener {
        void onDialogPositiveClick(DialogFragment dialog, float rating, String feedback);
    }

    private RateListener listener;
    private AppCompatRatingBar mRating;
    private AppCompatEditText mFeedback;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.rating_dialog, null);

        builder.setView(dialogView)
                .setPositiveButton(R.string.rating, (dialog, id) -> {
                    float rating = mRating.getRating();
                    String feedback = mFeedback.getText().toString().trim();
                    listener.onDialogPositiveClick(RatingDialog.this, rating, feedback);
                    RatingDialog.this.dismiss();
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> RatingDialog.this.getDialog().cancel());
        mRating = dialogView.findViewById(R.id.rating_bar);
        mFeedback = dialogView.findViewById(R.id.feedback_edit_text);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (RateListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement RateListener");
        }
    }
}