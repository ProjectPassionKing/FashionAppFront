package com.example.fashionapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.example.fashionapp.R;
import com.example.fashionapp.ViewModel.SharedViewModel;
import com.example.fashionapp.databinding.FragmentAllInOneBinding;

public class AllInOneFragment extends Fragment {
    String result; //진단 결과
    private FragmentAllInOneBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAllInOneBinding.inflate(inflater, container, false);

        Spinner spinner1 = binding.getRoot().findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(binding.getRoot().getContext(), R.layout.spinner_item, new String[]{"","체형 진단 진행 / 결과 보기", "코디 팁"});
        adapter1.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    NavHostFragment.findNavController(AllInOneFragment.this)
                            .navigate(R.id.action_global_DiagnosisSelectFragment);
                }

                if (position == 2) {
                    NavHostFragment.findNavController(AllInOneFragment.this)
                            .navigate(R.id.action_global_CordiTipFragment);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Spinner spinner2 = binding.getRoot().findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(binding.getRoot().getContext(), android.R.layout.simple_spinner_item, new String[]{"","내가 입은 옷과 비슷한 옷", "가상으로 옷 입어보기"});
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    NavHostFragment.findNavController(AllInOneFragment.this)
                            .navigate(R.id.action_global_TakePhotoFragment);
                }

                if (position == 2) {
                    NavHostFragment.findNavController(AllInOneFragment.this)
                            .navigate(R.id.action_global_TakeSimFragment);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

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
                NavHostFragment.findNavController(AllInOneFragment.this)
                        .navigate(R.id.action_global_toHome);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}