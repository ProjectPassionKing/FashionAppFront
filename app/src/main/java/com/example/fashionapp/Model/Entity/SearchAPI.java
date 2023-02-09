package com.example.fashionapp.Model.Entity;

import com.android.volley.RequestQueue;
import com.example.fashionapp.BuildConfig;
import com.example.fashionapp.Model.Entity.search.Product;
import com.example.fashionapp.Model.Entity.search.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public  interface SearchAPI {
    @GET("OpenApiService.tmall?apiCode=ProductSearch")
    Call<ProductResponse> searchRes(
        @Query("pageSize") String pageSize,
        @Query("keyword") String keyword,
        @Query("SortCd") String sortCd,
        @Query("key") String search_key
        );

}
