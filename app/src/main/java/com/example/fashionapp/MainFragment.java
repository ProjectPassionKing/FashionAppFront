package com.example.fashionapp;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.fashionapp.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        MoreHorizontalScrollView moreScroll = new MoreHorizontalScrollView(this.getContext());
        LinearLayout hscroll = binding.scrollview;
        hscroll.addView(moreScroll);

        ;
//스크롤뷰
//        binding.buttonThird.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(SecondFragment.this)
//                        .navigate(R.id.action_SecondFragment_to_ThirdFragment);
//            }
//        });
//
//        binding.buttonNinth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(SecondFragment.this)
//                        .navigate(R.id.action_SecondFragment_to_EightFragment);
//            }
//        });

        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainFragment.this)
                        .navigate(R.id.action_SecondFragment_to_NinthFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}