package com.example.fashionapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.fashionapp.databinding.FragmentRecommandBinding;

public class RecommandFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private FragmentRecommandBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentRecommandBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void playSound() {
        mediaPlayer.start();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mediaPlayer = MediaPlayer.create(this.getContext(), R.raw.straightrecom);
        playSound();

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RecommandFragment.this)
                        .navigate(R.id.action_SeventhFragment_to_SecondFragment);
            }
        });
        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RecommandFragment.this)
                        .navigate(R.id.action_SeventhFragment_to_NinthFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}