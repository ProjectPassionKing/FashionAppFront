package com.example.fashionapp;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.fashionapp.databinding.FragmentShowPhotoBinding;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ShowPhotoFragment extends Fragment {

    private FragmentShowPhotoBinding binding;
    ImageView click_image_id;
    TextView top;
    TextView bottom;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentShowPhotoBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        File mostRecentFile = null;
        long mostRecentModified = Long.MIN_VALUE;

        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        for (File file : storageDir.listFiles()) {
            if (file.isFile() && file.lastModified() > mostRecentModified) {
                mostRecentFile = file;
                mostRecentModified = file.lastModified();
            }
        }

        Bitmap bitmap = BitmapFactory.decodeFile(mostRecentFile.getAbsolutePath());
        click_image_id = getView().findViewById(R.id.click_image);
        click_image_id.setImageBitmap(bitmap);

        top = getView().findViewById(R.id.top);
        bottom = getView().findViewById(R.id.bottom);

        File finalMostRecentFile = mostRecentFile;

        new Thread(() ->
        {
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file","file.jpg", RequestBody.create(finalMostRecentFile, MultipartBody.FORM))
                    .build();

            Request request = new Request.Builder()
                    .url("http://172.23.247.89:5000/pred")
                    .post(requestBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String responseBodyString = response.body().string();
                System.out.println(responseBodyString);

                try {
                    JSONObject jsonObject = new JSONObject(responseBodyString);
                    String top_data = jsonObject.getString("top");
                    String bottom_data = jsonObject.getString("bottom");

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            top.setText(top_data);
                            bottom.setText(bottom_data);
                        }
                    });

                    response.close();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();


        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ShowPhotoFragment.this)
                        .navigate(R.id.action_ShowPhotoFragment_to_MainFragment);}
        });
        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ShowPhotoFragment.this)
                        .navigate(R.id.action_ShowPhotoFragment_to_AllinOneFragment);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}