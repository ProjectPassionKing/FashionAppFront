package com.example.fashionapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fashionapp.R;
import com.example.fashionapp.databinding.FragmentCordiTipBinding;

public class CordiTipFragment extends Fragment {
    private FragmentCordiTipBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentCordiTipBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CordiTipFragment.this)
                        .navigate(R.id.action_global_toHome);
            }
        });

        MoreHorizontalScrollView moreScrollView = new MoreHorizontalScrollView(this);
        binding.scrollview.addView(moreScrollView);

        //second, third 만들기
        binding.cordi2nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecommandFragment.topbottom = "상의";
                RecommandFragment.keyword = "브이넥 니트";
                NavHostFragment.findNavController(CordiTipFragment.this)
                        .navigate(R.id.action_global_RecommandFragment);
            }
        });

        binding.cordi3rd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RecommandFragment.topbottom = "하의";
                RecommandFragment.keyword = "일자핏 바지";
                NavHostFragment.findNavController(CordiTipFragment.this)
                        .navigate(R.id.action_global_RecommandFragment);
            }
        });
        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CordiTipFragment.this)
                        .navigate(R.id.action_global_AllInOneFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}