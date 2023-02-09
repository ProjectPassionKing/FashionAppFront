package com.example.fashionapp.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fashionapp.BuildConfig;
import com.example.fashionapp.Model.Entity.SearchAPI;
import com.example.fashionapp.Model.Entity.search.Product;
import com.example.fashionapp.Model.Entity.search.ProductResponse;
import com.tickaroo.tikxml.TikXml;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchViewModel extends AndroidViewModel {
    private MutableLiveData<List<Product>> searchresult = new MutableLiveData<>();
    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Product>> getData(){
        return searchresult;
    }

    public LiveData<List<Product>> getAPI(Context context, String keyword){
        String pSize = "10";
        String apiCode = "Product";
        String sortCd = "CP";
        String searchKey = BuildConfig.SEARCH_KEY;
        String baseUrl = "http://openapi.11st.co.kr/openapi/";

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(TikXmlConverterFactory.create(new TikXml.Builder().exceptionOnUnreadXml(false).build()))
                .client(client)
                .build();

        SearchAPI searchAPI = retrofit.create(SearchAPI.class);
        Call<ProductResponse> call = searchAPI.searchRes(pSize, keyword, sortCd, searchKey);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                ProductResponse presponse = response.body();
//                System.out.println(presponse.toString());
                List<Product> productList = presponse.getProducts().getProduct();
                searchresult.postValue(productList);
            }


            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return searchresult;
    }

}
