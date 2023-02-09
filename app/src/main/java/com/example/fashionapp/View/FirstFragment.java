package com.example.fashionapp.View;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fashionapp.Model.Entity.Weather;
import com.example.fashionapp.R;
import com.example.fashionapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        VideoView videoView = binding.getRoot().findViewById(R.id.avatar_video);
        Uri videoUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.avatar_video);
        videoView.setVideoURI(videoUri);
        MediaController mediaController = new MediaController(getActivity());
        videoView.setMediaController(mediaController);
        videoView.start();

        final int[] cnt = {0};
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
                cnt[0]++;
                if (cnt[0]==3){
                    videoView.stopPlayback();
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_MainFragment);
                }
            }
        });

        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_MainFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class WeatherView extends ConstraintLayout {
        public WeatherView(@NonNull Activity activity) {
            super(activity.getApplicationContext());
            init(activity);
        }

        private void init(Activity activity) {
            LayoutInflater inflater = (LayoutInflater) activity.getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.weather, this, true);
        }

        public void setWeatherUI(Weather weather){
            String c = getTxtfromStr(R.string.temperature);

            ((TextView) findViewById(R.id.temperature_txt)).setText(weather.getTmp()+c);
            ((TextView) findViewById(R.id.mtemperature_txt)).setText(weather.getMinTmp() +c + "/" + weather.getMaxTmp()+c);

            String sno = weather.getSnow();
            String sky = weather.getSky();
            String pty = weather.getRain();
            String pcp = weather.getRainper();

            if (!sno.equals("적설없응")){
                skycondition(sky, pty, sno);
            }
            skycondition(sky, pty, pcp);
        }

        private void skycondition(String sky, String pty, String pcp) {
            if (pty.equals("0")) {
                if (sky.equals("1")) {
                    changeweathertxt(getTxtfromStr(R.string.sky1), R.drawable.day_clear);
                }
                if (sky.equals("2")) {
                    changeweathertxt(getTxtfromStr(R.string.sky2), R.drawable.day_partial_cloud);
                }
                if (sky.equals("3")) {
                    changeweathertxt(getTxtfromStr(R.string.sky3), R.drawable.cloudy);
                }
                if (sky.equals("4")) {
                    changeweathertxt(getTxtfromStr(R.string.sky4), R.drawable.overcast);
                }
            }
            if (pty.equals("1")) {
                changeweathertxt(getTxtfromStr(R.string.rain) + pcp, R.drawable.rain);
            }
            if (pty.equals("2")) {
                changeweathertxt(getTxtfromStr(R.string.rainsnow) + pcp, R.drawable.sleet);
            }
            if (pty.equals("3")) {
                changeweathertxt(getTxtfromStr(R.string.snow) + pcp, R.drawable.snow);
            }
            if (pty.equals("4")) {
                changeweathertxt(getTxtfromStr(R.string.shower) + pcp, R.drawable.day_rain);
            }
        }

        private void changeweathertxt(String w, int icon) {
            ((TextView) findViewById(R.id.weather_txt)).setText(w);
            ((ImageView) findViewById(R.id.icon_img)).setImageResource(icon);
        }

        private String getTxtfromStr(int rString) {
            return this.getResources().getString(rString);
        }


    }
}