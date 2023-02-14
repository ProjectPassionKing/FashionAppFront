package com.example.fashionapp.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import com.example.fashionapp.Model.Entity.search.Product;
import com.example.fashionapp.R;
import com.example.fashionapp.ViewModel.SearchKeywordViewModel;
import com.example.fashionapp.ViewModel.SearchViewModel;
import com.example.fashionapp.databinding.FragmentRecommandBinding;

public class RecommandFragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private FragmentRecommandBinding binding;
    public static String topbottom;
    private SearchViewModel searchViewModel;
    public String keyword = "";
    private SearchKeywordViewModel searchKeywordViewModel;
    private SearchLayout searchLayout;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentRecommandBinding.inflate(inflater, container, false);
        binding.adotTalkTxtview.setText(String.format(getResources().getString(R.string.recommand_cl), topbottom));

        return binding.getRoot();
    }

    public void playSound() {
        mediaPlayer.start();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getAPIResult();

        mediaPlayer = MediaPlayer.create(this.getContext(), R.raw.straightrecom);
        playSound();

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RecommandFragment.this)
                        .navigate(R.id.action_global_toHome);
            }
        });
        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RecommandFragment.this)
                        .navigate(R.id.action_global_AllInOneFragment);
            }
        });

        binding.otherRecommandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.searchLinear.removeAllViews();
                searchKeywordViewModel.chooseKeyword();
            }
        });

        MoreHorizontalScrollView moreScrollView = new MoreHorizontalScrollView(this);
        binding.scrollview.addView(moreScrollView);
    }

    private void getAPIResult() {
        searchKeywordViewModel = new ViewModelProvider(requireActivity()).get(SearchKeywordViewModel.class);
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        searchKeywordViewModel.chooseKeyword().observe(getViewLifecycleOwner(), searchkeyword ->{
            keyword = searchkeyword;
            new Thread(() -> {
                try {
                    searchViewModel.callAPI(keyword);
                } catch (XmlPullParserException | IOException e) {
                    e.printStackTrace();
                }
            }).start();
        });

        searchViewModel.getData().observe(getViewLifecycleOwner(), product -> {
            for (Product p : product) {
                try {
                    searchLayout = new SearchLayout(getContext(), p);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                binding.searchLinear.addView(searchLayout);
                gotoProductpage(searchLayout, p);
            }
        });

    }

    private void gotoProductpage(SearchLayout searchLayout, Product p) {
        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(p.getProductDetailUrl()));
                startActivity(intent);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}