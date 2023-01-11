package com.example.fashionapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MoreHorizontalScrollView extends LinearLayout {
    public MoreHorizontalScrollView(Context context) {
        super(context);
        init(context);
    }

    public MoreHorizontalScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.scroll, this, true);
    }
}
