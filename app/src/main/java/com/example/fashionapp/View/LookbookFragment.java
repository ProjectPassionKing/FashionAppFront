package com.example.fashionapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fashionapp.R;
import com.example.fashionapp.ViewModel.SharedViewModel;
import com.example.fashionapp.databinding.FragmentLookbookBinding;

public class LookbookFragment extends Fragment {

    private FragmentLookbookBinding binding;
    ImageView box_image;
    SharedViewModel sharedViewModel;
    boolean selected;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLookbookBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        box_image = getView().findViewById(R.id.box_image);
        selected = false;

        binding.guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add change button
                binding.guest.setSelected(!selected);
                selected = !selected;
            }
        });

        binding.casual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add change button
                binding.casual.setSelected(!selected);
                selected = !selected;
            }
        });

        binding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add change button
                binding.date.setSelected(!selected);
                selected = !selected;
            }
        });

        binding.sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add change button
                binding.sports.setSelected(!selected);
                selected = !selected;
            }
        });

        binding.work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add change button
                binding.work.setSelected(!selected);
                selected = !selected;
            }
        });

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LookbookFragment.this)
                        .navigate(R.id.action_global_toHome);}
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        binding = null;
    }

}