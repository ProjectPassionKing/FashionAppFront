package com.example.fashionapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fashionapp.databinding.FragmentDiagnosisBinding;

public class DiagnosisFragment extends Fragment {

    private FragmentDiagnosisBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDiagnosisBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DiagnosisFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_SecondFragment);
            }
        });
        binding.answer1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DiagnosisFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FourthFragment);
            }
        });
        binding.answer2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DiagnosisFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FourthFragment);
            }
        });
        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DiagnosisFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_NinthFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}