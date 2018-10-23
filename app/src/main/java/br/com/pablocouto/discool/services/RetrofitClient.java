package br.com.pablocouto.discool.services;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pablo on 17/05/2017.
 */

public class RetrofitClient {

    public static Retrofit getClient(String baseUrl) {
     Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.i("INFO: ", "Initialized retrofit instance on base: " + retrofit.baseUrl());
        return retrofit;
    }
}

