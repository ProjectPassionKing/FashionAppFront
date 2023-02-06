package com.example.fashionapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.fashionapp.databinding.FragmentRecommandBinding;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecommandFragment extends Fragment {
    private List<Product> productList;
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
                        .navigate(R.id.action_RecommandFragment_to_MainFragment);
            }
        });
        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RecommandFragment.this)
                        .navigate(R.id.action_RecommandFragment_to_AllinOneFragment);
            }
        });

        productList = new ArrayList<Product>();

        ProductSearchService service = new ProductSearchService("브이넥");
//        ProductSearchThread pthread = new ProductSearchThread(service, handler);
//        pthread.start();

        MoreHorizontalScrollView moreScrollView = new MoreHorizontalScrollView(this);
        new Thread(()->{
            try {
                service.search();
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }
        }).start();
        binding.scrollview.addView(moreScrollView);
    }

//    private Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 1) {
//                if (msg.arg2 == 10) {
//                    productList.clear();
//                    productList.addAll((List<Product>) msg.obj);
//                }
//            }
//        }
//    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}