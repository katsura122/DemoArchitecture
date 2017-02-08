package com.katsuraf.demoarchitecture.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.katsuraf.demoarchitecture.constants.ApiConstant;
import com.katsuraf.demoarchitecture.net.converter.ResponseConverterFactory;

import retrofit2.Retrofit;

public class RestClient {

    public static Retrofit newInstance() {
        return new Retrofit.Builder().baseUrl(ApiConstant.END_POINT)
                .addConverterFactory(ResponseConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClientProvider.getSimpleClient())
                .build();
    }

//    private ApiService apiService;

//    public RestClient(Context context) {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiConstant.END_POINT)
//                .addConverterFactory(ResponseConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(new OkHttpClientProvider(context).getOkHttpClient())
//                .build();
//        apiService = retrofit.create(ApiService.class);
//    }
//
//    public ApiService getApiService() {
//        return apiService;
//    }

}
