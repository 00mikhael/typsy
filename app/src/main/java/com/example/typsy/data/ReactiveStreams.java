package com.example.typsy.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;

import io.reactivex.Flowable;

/**
 * Created by gravity on 10/25/17.
 */

public class ReactiveStreams<T> {
    public LiveData<T> toLiveData(Flowable<T> responseFlowable) {
        return LiveDataReactiveStreams.fromPublisher(responseFlowable);
    }
}
