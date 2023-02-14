package com.example.fashionapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.request.RequestCoordinator;
import com.example.fashionapp.R;
import com.example.fashionapp.ViewModel.SearchKeywordViewModel;
import com.example.fashionapp.ViewModel.SharedViewModel;
import com.example.fashionapp.databinding.FragmentCordiTipBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CordiTipFragment extends Fragment {
    private FragmentCordiTipBinding binding;
    String result;
    SearchKeywordViewModel searchKeywordViewModel;

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
        searchKeywordViewModel = new ViewModelProvider(requireActivity()).get(SearchKeywordViewModel.class);

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
            binding.cordi1st.setText(getStringById("cordi", "total"));
            binding.cordi2nd.setText(getStringById("cordi", "top"));
            binding.cordi3rd.setText(getStringById("cordi", "bottom"));
        });
        MoreHorizontalScrollView moreScrollView = new MoreHorizontalScrollView(this);
        binding.scrollview.addView(moreScrollView);



        //second, third 만들기
        binding.cordi2nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                RecommandFragment.topbottom = "상의";
                sendKeyList("top", "상의");
//                RecommandFragment.keyword = getStringById("keyword", "top");
                NavHostFragment.findNavController(CordiTipFragment.this)
                        .navigate(R.id.action_global_RecommandFragment);
            }
        });

        binding.cordi3rd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                RecommandFragment.topbottom = "하의";
                sendKeyList("bottom", "하의");
//                RecommandFragment.keyword = getStringById("keyword", "bottom");
                NavHostFragment.findNavController(CordiTipFragment.this)
                        .navigate(R.id.action_global_RecommandFragment);
            }
        });
    }

    private void sendKeyList(String tb, String ktb) {
        RecommandFragment.topbottom = ktb;
//        RecommandFragment.keylist = Arrays.asList(getStringById("keyword", tb).split(","));
        searchKeywordViewModel.setFirstKeyList(new ArrayList<>(Arrays.asList(getStringById("keyword", tb).split(","))));
    }

    private String getStringById(String category, String part) {
        int id =  getResources().getIdentifier(
                result+"_"+category+"_"+part, "string", getActivity().getPackageName());
        return getResources().getString(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}