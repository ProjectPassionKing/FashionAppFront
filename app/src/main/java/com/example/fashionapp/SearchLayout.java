package com.example.fashionapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.fashionapp.databinding.SearchresultBinding;

import java.util.Map;

public class SearchLayout extends LinearLayout {
    private SearchresultBinding binding;

    public SearchLayout(Context context) {
        super(context);
    }

    public SearchLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public SearchLayout(Context context, Product p){
        super(context);
        init(context, p);
    }

    private void init(Context context, Product searchresult){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.searchresult, this, true);

        String imgurl = searchresult.getProductImage();
        ImageView imgv = (ImageView) findViewById(R.id.search_img);
        TextView txtview = (TextView) findViewById(R.id.search_name);
        Glide.with(this).load(imgurl).into(imgv);
        System.out.println(searchresult.getProductDetailUrl());
        txtview.setText(searchresult.getProductName().substring(0, 20)+"...");

    }
}
