package com.example.typsy.data;

import com.example.typsy.data.local.entity.Price;

/**
 * Created by gravity on 11/8/17.
 */

public interface Callback {

    interface ApiCallbak {
        void onApiResponse(Price p);

        void apiError(Throwable t);
    }

    interface DBCallbak {
        void initialData(Price p);

        void finalData(Price p);

        void dbError(Throwable t);

        void onApiError(Throwable t);

        void onComplete();
    }

    interface VmCallback {
        void onResponse(Resource<Price> resource);
    }
}



