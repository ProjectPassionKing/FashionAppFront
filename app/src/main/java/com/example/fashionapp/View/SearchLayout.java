package com.example.fashionapp.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.example.fashionapp.Model.Entity.search.Product;
import com.example.fashionapp.R;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SearchLayout extends LinearLayout {

    public SearchLayout(Context context) {
        super(context);
    }
    public SearchLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public SearchLayout(Context context, Product p) throws UnsupportedEncodingException {
        super(context);
        init(context, p);
    }

    private void init(Context context, Product searchresult) throws UnsupportedEncodingException {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.searchresult, this, true);

        Glide.with(this).load(searchresult.getProductImage300()).into((ImageView) findViewById(R.id.search_img));
//        new String(response.body().bytes(), "euc-kr");
        String name = searchresult.getProductName();
        String pname = new String(searchresult.getProductName().getBytes(StandardCharsets.UTF_8));
        if (pname.length()>18) pname = pname.substring(0, 17)+"…";
        ((TextView) findViewById(R.id.search_name)).setText(pname);

        String discount = searchresult.getBenefit().getDiscount();
        if (!discount.equals("0"))
            ((TextView)findViewById(R.id.product_price)).setText("₩"+searchresult.getSalePrice()+'\n'+discount +"원 할인중!");
    }
}
