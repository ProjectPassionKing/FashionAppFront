package com.example.fashionapp.View;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.fashionapp.R;
import com.example.fashionapp.databinding.FragmentResultBinding;

public class ResultFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private FragmentResultBinding binding;
    public static String result;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentResultBinding.inflate(inflater, container, false);

        ImageView gifImageView = binding.getRoot().findViewById(R.id.diagnose_video);
        Glide.with(this).asGif().load(R.raw.diagnose_video).into(gifImageView);

        binding.questionTxtview.setText(result);
        return binding.getRoot();

    }

    public void playSound() {
        mediaPlayer.start();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mediaPlayer = MediaPlayer.create(this.getContext(), R.raw.straightresult);
        playSound();


        MoreHorizontalScrollView moreScrollView = new MoreHorizontalScrollView(this);
        binding.scrollview.addView(moreScrollView);

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ResultFragment.this)
                        .navigate(R.id.action_ResultFragment_to_MainFragment);
            }
        });

        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ResultFragment.this)
                        .navigate(R.id.action_ResultFragment_to_AllinOneFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}