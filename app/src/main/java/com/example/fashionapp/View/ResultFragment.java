package com.example.fashionapp.View;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.bumptech.glide.Glide;
import com.example.fashionapp.R;
import com.example.fashionapp.ViewModel.SharedViewModel;
import com.example.fashionapp.databinding.FragmentResultBinding;

public class ResultFragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private FragmentResultBinding binding;
    public static int result_audio;
    String diagnosis_result;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentResultBinding.inflate(inflater, container, false);

        ImageView gifImageView = binding.getRoot().findViewById(R.id.diagnose_video);
        Glide.with(this).asGif().load(R.raw.diagnose_video).into(gifImageView);

        return binding.getRoot();

    }

    public void playSound() {
        mediaPlayer.start();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getDiagnosisResult().observe(getViewLifecycleOwner(), dresult ->{
            diagnosis_result = dresult.toLowerCase();
            binding.resultTxtview.setText(dresult);
            binding.resultExp1.setText(getStringById("total"));
            binding.resultExp2.setText(getStringById("fabric"));
            binding.resultExp3.setText(getStringById("pattern"));
        });

        mediaPlayer = MediaPlayer.create(this.getContext(), result_audio);
        playSound();

        MoreHorizontalScrollView moreScrollView = new MoreHorizontalScrollView(this);
        binding.scrollview.addView(moreScrollView);

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ResultFragment.this)
                        .navigate(R.id.action_global_toHome);
            }
        });
    }

    private String getStringById(String category) {
        int id =  getResources().getIdentifier(
                diagnosis_result+"_"+category, "string", getActivity().getPackageName());
        return getResources().getString(id);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}