package com.example.typsy.ui.screens.about;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.typsy.data.local.entity.Rating;
import com.example.typsy.mail.GMailSender;

/**
 * Created by gravity on 11/9/17.
 */

public class AboutViewModel extends AndroidViewModel {
    private MutableLiveData<String> vmSnackMessage = new MutableLiveData<>();

    public AboutViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getVmSnackMessage() {
        return vmSnackMessage;
    }

    public void setVmSnackMessage(String message) {
        vmSnackMessage.setValue(message);
    }

    public void sendMail(Rating rating) {
        new Thread(() -> {
            try {
                GMailSender sender = new GMailSender(
                        "ifeanyimcn@gmail.com",
                        "");
                sender.sendMail(
                        "NEW TYPSY RATING",
                        "\n" + "Feedback : \n" + rating.getFeedback() + "\n\n" + "Rating : " + rating.getRating(),
                        "ifeanyimcn@gmail.com",
                        "mikalil525@gmail.com");
            } catch (Exception e) {}
        }).start();
    }
}
