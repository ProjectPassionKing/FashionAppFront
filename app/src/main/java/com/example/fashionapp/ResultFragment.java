package com.example.fashionapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.fashionapp.databinding.FragmentResultBinding;

public class ResultFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private FragmentResultBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentResultBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void playSound() {
        mediaPlayer.start();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mediaPlayer = MediaPlayer.create(this.getContext(), R.raw.straightresult);
        playSound();
//홈이랑 메뉴 버튼은 다른 화면에서도 계속 쓸 거면 클래스나 함수화해서 밖으로 빼는 게 낫지 않나
        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ResultFragment.this)
                        .navigate(R.id.action_FourthFragment_to_SecondFragment);
            }
        });
//        binding.scrollview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FourthFragment.this)
//                        .navigate(R.id.action_FourthFragment_to_FifthFragment);
//            }
//        });
        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ResultFragment.this)
                        .navigate(R.id.action_FourthFragment_to_NinthFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}