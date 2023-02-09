package com.example.fashionapp.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.List;

import com.example.fashionapp.Model.Product;
import com.example.fashionapp.Model.ProductSearchService;
import com.example.fashionapp.R;
import com.example.fashionapp.SearchLayout;
import com.example.fashionapp.databinding.FragmentRecommandBinding;

public class RecommandFragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private FragmentRecommandBinding binding;
    public static String topbottom;

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

        String keyword = "여자 브이넥 니트"; //앞 fragment에서 받아올 것
        searchThread(keyword, topbottom);

        MoreHorizontalScrollView moreScrollView = new MoreHorizontalScrollView(this);

        binding.scrollview.addView(moreScrollView);
    }

    private void searchThread(String keyword, String topbottom){
        new Thread(() -> {
            ProductSearchService service = new ProductSearchService(keyword);
            try {
                List<Product> productList = service.search();
                addSearchView(productList, topbottom);
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void addSearchView(List<Product> productList, String topbottom){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (Product p : productList) {
                    SearchLayout searchLayout = new SearchLayout(getContext(), p);
                    binding.searchLinear.addView(searchLayout);

                    binding.adotTalkTxtview.setText(String.format(getResources().getString(R.string.recommand_cl),topbottom));
                    gotoProductpage(searchLayout, p);
                }
            }
        });
    }

    private void gotoProductpage(SearchLayout searchLayout, Product p){
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