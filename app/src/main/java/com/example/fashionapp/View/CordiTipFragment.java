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
import com.example.fashionapp.databinding.FragmentCordiTipBinding;

import java.util.Locale;

public class CordiTipFragment extends Fragment {
    private FragmentCordiTipBinding binding;
    String result;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentCordiTipBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CordiTipFragment.this)
                        .navigate(R.id.action_global_toHome);
            }
        });
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getResult().observe(getViewLifecycleOwner(), dresult->{
            result = dresult.toLowerCase();
            binding.adotTalkTxtview.setText(String.format(getResources().getString(R.string.cordi_tip), dresult));
            binding.cordi1st.setText(getResources().getString(getStringId("total")));
            binding.cordi2nd.setText(getResources().getString(getStringId("top")));
            binding.cordi3rd.setText(getResources().getString(getStringId("bottom")));

        });
        MoreHorizontalScrollView moreScrollView = new MoreHorizontalScrollView(this);
        binding.scrollview.addView(moreScrollView);

        //second, third 만들기
        binding.cordi2nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecommandFragment.topbottom = "상의";
                RecommandFragment.keyword = "브이넥 니트";
                NavHostFragment.findNavController(CordiTipFragment.this)
                        .navigate(R.id.action_global_RecommandFragment);
            }
        });

        binding.cordi3rd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RecommandFragment.topbottom = "하의";
                RecommandFragment.keyword = "일자핏 바지";
                NavHostFragment.findNavController(CordiTipFragment.this)
                        .navigate(R.id.action_global_RecommandFragment);
            }
        });
        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CordiTipFragment.this)
                        .navigate(R.id.action_global_AllInOneFragment);
            }
        });
    }

    private int getStringId(String part) {
        return getResources().getIdentifier(
                result+"_cordi_"+part, "string", getActivity().getPackageName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}