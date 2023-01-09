package com.example.fashionapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fashionapp.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DetailFragment.this)
                        .navigate(R.id.action_FifthFragment_to_SecondFragment);
            }
        });
        //스크롤 뷰이므로 따로 만들어서 뺄 것
//        binding.scrollview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FifthFragment.this)
//                        .navigate(R.id.action_FifthFragment_to_SixthFragment);
//            }
//        });
        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DetailFragment.this)
                        .navigate(R.id.action_FifthFragment_to_NinthFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}