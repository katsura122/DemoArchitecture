package com.katsuraf.demoarchitecture.net;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.katsuraf.demoarchitecture.constants.ApiConstant;
import com.katsuraf.demoarchitecture.net.converter.ResponseConverterFactory;

import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Retrofit;

@Singleton
public class RestClient {

    private ApiService apiService;

    public RestClient(Context context) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiConstant.BASE_URI)
                .addConverterFactory(ResponseConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClientProvider(context).getOkHttpClient())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }

}
