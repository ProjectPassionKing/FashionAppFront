package com.example.fashionapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class SearchAPI extends HorizontalScrollView {

    public SearchAPI(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.search_api, this, true);
    }

    public void callapi(Activity activity, String keyword){
        new Thread(()->{
            ProductSearchService service = new ProductSearchService(keyword);
            try {
                List<Product> result = service.search();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setView(result);
                    }
                });
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private void setView(List<Product> result){
        String imgurl = result.get(0).getProductImage();
        ImageView imgv = findViewById(R.id.search_img);
        Glide.with(this).load(imgurl).into(imgv);
        ((TextView) findViewById(R.id.search_name)).setText(result.get(0).getProductName().substring(0,20));
    }
}
