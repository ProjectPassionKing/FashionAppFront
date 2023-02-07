package com.example.fashionapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        String keyword = "브이넥";
        new Thread(() -> {
            ProductSearchService service = new ProductSearchService(keyword);
            try {
                List<Product> productList = service.search();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (Product p : productList) {
                            SearchLayout searchLayout = new SearchLayout(getContext(), p);
                            binding.searchLinear.addView(searchLayout);
                            searchLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse(p.getProductDetailUrl()));
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }

        }).start();

        MoreHorizontalScrollView moreScrollView = new MoreHorizontalScrollView(this);

        binding.scrollview.addView(moreScrollView);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}