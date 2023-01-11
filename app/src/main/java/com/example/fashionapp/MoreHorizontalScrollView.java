package com.example.fashionapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fashionapp.databinding.ScrollBinding;

import org.jetbrains.annotations.NotNull;

//@SuppressLint("ViewConstructor")
public class MoreHorizontalScrollView extends LinearLayout {
    private ScrollBinding binding;
    private MainFragment mainFragment;


    public MoreHorizontalScrollView(Fragment fragment) {
        super(fragment.getContext());
        init(fragment);
    }

    public MoreHorizontalScrollView(Fragment fragment, @Nullable AttributeSet attrs) {
        super(fragment.getContext(), attrs);
        init(fragment);
    }

    private void init(@NotNull Fragment fragment){
        //context null 처리 나중에
        LayoutInflater inflater = (LayoutInflater) fragment.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.scroll, this, true);

        findViewById(R.id.more_request_btn1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("******* more request btn 1 clicked!! ******");
                //navigator fragment 이름 때문에 구분이 안 됨...
                NavHostFragment.findNavController(fragment)
                        .navigate(R.id.action_SecondFragment_to_ThirdFragment);
            }
        });
    }

}
