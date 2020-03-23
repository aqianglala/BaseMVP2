package com.qiang.basemvp.net;


import com.qiang.library.net.BaseHttpHelper;

public class HttpHelper {

    private static final class RxRestServiceHolder {
        private static final ApiService REST_SERVICE =
                BaseHttpHelper.RetrofitHolder.RETROFIT_CLIENT.create(ApiService.class);
    }

    public static ApiService getRxRestService() {
        return RxRestServiceHolder.REST_SERVICE;
    }
}
