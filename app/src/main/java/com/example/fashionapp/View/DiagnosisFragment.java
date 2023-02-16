package com.example.fashionapp.View;

import android.media.MediaPlayer;
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
import com.example.fashionapp.databinding.FragmentDiagnosisBinding;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DiagnosisFragment extends Fragment {
    SharedViewModel sharedViewModel;

    private MediaPlayer mediaPlayer;
    private FragmentDiagnosisBinding binding;
    private int page;
    Map<String, Integer> map = new HashMap<String, Integer>();

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        page = 0;
        map.put("A", 0);
        map.put("B", 0);
        map.put("C", 0);
        binding = FragmentDiagnosisBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void playSound() {
        mediaPlayer.start();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mediaPlayer = MediaPlayer.create(this.getContext(), R.raw.diagnose);
        playSound();

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DiagnosisFragment.this)
                        .navigate(R.id.action_global_toHome);
            }
        });
        binding.answer1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page += 1;
                map.put("A", map.get("A")+1);
                getPage();
            }
        });
        binding.answer2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                page += 1;
                map.put("B", map.get("B")+1);
                getPage();
            }
        });
        binding.answer3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page += 1;
                map.put("C", map.get("C")+1);
                getPage();
            }
        });
    }

    private void getPage() {
        if (page > 12) {
            Map.Entry<Character, Integer> maxEntry = null;
            int maxValue = Collections.max(map.values());
            String maxKey = "";
            int maxAudio = 0;
            ArrayList<String> keys = new ArrayList<>();
            int count = 0;

            for (String key : map.keySet()) {
                Integer value = map.get(key);
                if (value == maxValue) {
                    maxKey = key;
                    keys.add(key);
                    count++;
                }
            }

            if (count > 1) {
                switch (keys.toString()) {
                    case "[A, B]":
                        page = 15;
                        binding.answer3Btn.setVisibility(View.INVISIBLE);
                        break;
                    case "[B, C]":
                        page = 14;
                        binding.answer1Btn.setVisibility(View.INVISIBLE);
                        break;
                    case "[A, C]":
                        page = 13;
                        binding.answer2Btn.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        System.out.println("error");
                        break;
                }
                int resIdQ = getResources().getIdentifier("question_"+(page+1),
                        "string", getActivity().getPackageName());
                int resIdA = getResources().getIdentifier("answer"+(page+1)+"_a",
                        "string", getActivity().getPackageName());
                int resIdB = getResources().getIdentifier("answer"+(page+1)+"_b",
                        "string", getActivity().getPackageName());
                int resIdC = getResources().getIdentifier("answer"+(page+1)+"_c",
                        "string", getActivity().getPackageName());

                binding.resultTxtview.setText(getResources().getString(resIdQ));
                binding.answer1Btn.setText(getResources().getString(resIdA));
                binding.answer2Btn.setText(getResources().getString(resIdB));
                binding.answer3Btn.setText(getResources().getString(resIdC));
                return;
            }
            
            switch (maxKey) {
                case "B":
                    maxKey = "Wave";
                    maxAudio = R.raw.waveresult;
                    break;
                case "C":
                    maxKey = "Natural";
                    maxAudio = R.raw.naturalresult;
                    break;
                default:
                    maxKey = "Straight";
                    maxAudio = R.raw.straightresult;
                    break;
            }

            ResultFragment.result_audio = maxAudio;

            sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
            sharedViewModel.setDiagnosisResult(maxKey);

            NavHostFragment.findNavController(DiagnosisFragment.this)
                    .navigate(R.id.action_global_ResultFragment);
        } else {
            int resIdQ = getResources().getIdentifier("question_"+(page+1),
                    "string", getActivity().getPackageName());
            int resIdA = getResources().getIdentifier("answer"+(page+1)+"_a",
                    "string", getActivity().getPackageName());
            int resIdB = getResources().getIdentifier("answer"+(page+1)+"_b",
                    "string", getActivity().getPackageName());
            int resIdC = getResources().getIdentifier("answer"+(page+1)+"_c",
                    "string", getActivity().getPackageName());

            binding.resultTxtview.setText(getResources().getString(resIdQ));
            binding.answer1Btn.setText(getResources().getString(resIdA));
            binding.answer2Btn.setText(getResources().getString(resIdB));
            binding.answer3Btn.setText(getResources().getString(resIdC));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
