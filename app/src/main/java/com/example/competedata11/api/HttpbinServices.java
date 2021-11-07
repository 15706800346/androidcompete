package com.example.competedata11.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HttpbinServices {

    @GET("/prod-api/press/press/list")
    Call<ResponseBody> getNewsList();

    @GET("/prod-api/api/traffic/rotation/list")
    Call<ResponseBody> getNewsBanner();

    @GET("/prod-api/api/rotation/list")
    Call<ResponseBody> getHomeBanner();
}
