package com.example.fashionapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.fashionapp.BuildConfig;
import com.example.fashionapp.Model.Entity.search.ProductBenefit;
import com.example.fashionapp.Model.SearchAPI;
import com.example.fashionapp.Model.Entity.search.Product;
import com.example.fashionapp.Model.Entity.search.ProductResponse;
import com.tickaroo.tikxml.TikXml;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchViewModel extends AndroidViewModel {
    private MutableLiveData<List<Product>> searchresult = new MutableLiveData<>();

    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Product>> getData() {
        return searchresult;
    }

    public LiveData<List<Product>> callAPI(String keyword) throws XmlPullParserException, IOException {


        String rurl = "http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key=";
        String psize = "10";
        String otherurl = "&pageSize=" + psize + "&apiCode=ProductSearch&sortCd=CP&keyword=";
        String key = BuildConfig.SEARCH_KEY;
        List<Product> list = null;
        URL url = new URL(rurl + key + otherurl + keyword);
        URLConnection urlCon = url.openConnection();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        parser.setInput(new InputStreamReader(urlCon.getInputStream(), "EUC-KR"));

        int eventType = parser.getEventType();
        Product p = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                //1
                case XmlPullParser.START_DOCUMENT: //0
                    list = new ArrayList<Product>();
                    break;
                case XmlPullParser.END_TAG: //3
                    String tag = parser.getName();
                    if (tag.equals("Product")) {
                        list.add(p);
                        p = null; //초기화
                    }
                case XmlPullParser.START_TAG: //2
                    p = setProduct(parser, p);
            }

            eventType = parser.next();
        }

        assert p != null;
        searchresult.postValue(list);
        return searchresult;
    }

    @Nullable
    private Product setProduct(XmlPullParser parser, Product p) throws IOException, XmlPullParserException {
        String tag;
        tag = parser.getName();
        switch (tag) {
            case "Product":
                p = new Product();
                break;
            case "ProductCode":
                if (p != null)
                    p.setProductCode(parser.nextText());
                break;
            case "ProductName":
                assert p != null;
                p.setProductName(parser.nextText());
                break;
            case "ProductImage300":
                if (p != null)
                    p.setProductImage300(parser.nextText());
                break;
            case "ProductPrice":
                if (p != null)
                    p.setProductPrice(parser.nextText());
                break;
            case "DetailPageUrl":
                assert p != null;
                p.setProductDetailUrl(parser.nextText());
                break;
            case "SalePrice":
                assert p != null;
                p.setSalePrice(parser.nextText());
                break;
            case "Discount":
                assert p != null;
                ProductBenefit benefit = new ProductBenefit();
                benefit.setDiscount(parser.nextText());
                p.setBenefit(benefit);
                break;
        }
        return p;
    }

    public LiveData<List<Product>> getAPI(String keyword) throws XmlPullParserException, IOException {
        String pSize = "10";
        String sortCd = "CP";
        String searchKey = BuildConfig.SEARCH_KEY;
        String baseUrl = "http://openapi.11st.co.kr/openapi/";

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
//                .addConverterFactory((Converter.Factory) XmlPullParserFactory.newInstance().newSerializer())
                .addConverterFactory(TikXmlConverterFactory.create(new TikXml.Builder().exceptionOnUnreadXml(false).build()))
                .client(client)
                .build();
        SearchAPI searchAPI = retrofit.create(SearchAPI.class);
        Call<ProductResponse> call = searchAPI.searchRes(pSize, keyword, sortCd, searchKey);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

                ProductResponse presponse = response.body();
                List<Product> productList = presponse.getProducts().getProduct();
                for (Product p : productList) {
                    try {
                        String name = new String(p.getProductName().getBytes(), "euc-kr");
                        String name2 = new String(name.getBytes(StandardCharsets.UTF_8));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
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
