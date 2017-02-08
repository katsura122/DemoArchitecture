package com.katsuraf.demoarchitecture.net;

import com.katsuraf.demoarchitecture.request.MainListRequest;
import com.katsuraf.demoarchitecture.response.ListResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("GetListData")
    Observable<ListResponse> getListData(@Body MainListRequest type);
}
