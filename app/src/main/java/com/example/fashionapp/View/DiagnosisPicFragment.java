package com.example.fashionapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fashionapp.R;
import com.example.fashionapp.ViewModel.SharedViewModel;
import com.example.fashionapp.databinding.FragmentDiagnosisPicBinding;


public class DiagnosisPicFragment extends Fragment {
    String result;
    private @NonNull
    FragmentDiagnosisPicBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDiagnosisPicBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getResult().observe(getViewLifecycleOwner(), dresult ->{
            result = dresult;
        });
        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DiagnosisPicFragment.this)
                        .navigate(R.id.action_DiagnosisPicFragment_to_MainFragment);
            }
        });

        binding.straightPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = "Straight";
                ResultFragment.result_audio = R.raw.straightresult;
                NavHostFragment.findNavController(DiagnosisPicFragment.this)
                        .navigate(R.id.action_DiagnosisPicFragment_to_ResultFragment);
            }
        });
        binding.naturalPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = "Natural";
                ResultFragment.result_audio = R.raw.naturalresult;
                NavHostFragment.findNavController(DiagnosisPicFragment.this)
                        .navigate(R.id.action_DiagnosisPicFragment_to_ResultFragment);
            }
        });
        binding.wavePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = "Wave";
                ResultFragment.result_audio = R.raw.waveresult;
                NavHostFragment.findNavController(DiagnosisPicFragment.this)
                        .navigate(R.id.action_DiagnosisPicFragment_to_ResultFragment);
            }
        });
        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DiagnosisPicFragment.this)
                        .navigate(R.id.action_DiagnosisPicFragment_to_AllinOneFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
