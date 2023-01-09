package com.example.fashionapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fashionapp.databinding.FragmentAllInOneBinding;

public class AllInOneFragment extends Fragment {

    private FragmentAllInOneBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAllInOneBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AllInOneFragment.this)
                        .navigate(R.id.action_NinthFragment_to_SecondFragment);
            }
        });
        binding.buttonEleventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AllInOneFragment.this)
                        .navigate(R.id.action_NinthFragment_to_ThirdFragment);
            }
        });
        binding.buttonTwelfth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AllInOneFragment.this)
                        .navigate(R.id.action_NinthFragment_to_SeventhFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}