package com.example.fashionapp;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.fashionapp.databinding.FragmentMainBinding;
import org.json.JSONException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


@RequiresApi(api = Build.VERSION_CODES.O)
public class MainFragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private FragmentMainBinding binding;
    private Map<String, String> result;
    Disposable backgroundTask;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void playSound() {
        mediaPlayer.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mediaPlayer = MediaPlayer.create(this.getContext(), R.raw.morning);
        playSound();
        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainFragment.this)
                        .navigate(R.id.action_MainFragment_to_FirstFragment);
            }
        });
        MoreHorizontalScrollView moreScrollView = new MoreHorizontalScrollView(this);
        binding.scrollview.addView(moreScrollView);

        WeatherView weatherView = new WeatherView(getContext());
        try {
            weatherView.connectapi(getContext(), getActivity());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        binding.weatherApi.addView(weatherView);

        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainFragment.this)
                        .navigate(R.id.action_MainFragment_to_AllinOneFragment);
            }
        });
    }

//    @SuppressLint("NewApi")
//    private void asyncapi(){
//        //onPreExecute(task 시작 전 실행될 코드 여기에 작성)
//        //api 호출
//        result = weatherView.getResult();
//
//        backgroundTask = Observable.fromCallable(() -> {
//                    //doInBackground(task에서 실행할 코드 여기에 작성)
//                    weatherView.weather(result);
//                    return true;
//
//                }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers
//                        .mainThread()).subscribe((none)->{
//                    //onPostExecute(task 끝난 후 실행될 코드 여기에 작성
//                    binding.weatherApi.addView(weatherView);
//
//                    backgroundTask.dispose();
//                    });
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}