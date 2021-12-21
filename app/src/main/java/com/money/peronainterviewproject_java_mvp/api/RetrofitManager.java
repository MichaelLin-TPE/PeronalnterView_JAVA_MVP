package com.money.peronainterviewproject_java_mvp.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {


    private static final String BASE_URL = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/";

    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    private static final Retrofit retrofit = retrofitBuilder.build();

    private static final RequestApi requestApi = retrofit.create(RequestApi.class);

    public static RequestApi getRequestApi(){
        return requestApi;
    }

}
