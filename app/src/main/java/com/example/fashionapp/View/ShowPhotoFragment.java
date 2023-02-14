package com.example.fashionapp.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fashionapp.R;
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
    ImageView top_image;
    ImageView bottom_image;

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
        top_image = getView().findViewById(R.id.top_image);
        bottom_image = getView().findViewById(R.id.bottom_image);

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
                String jsonString = response.body().string();

                JSONObject files = new JSONObject(jsonString);
                String file1_encoded = files.getString("file1");
                String file2_encoded = files.getString("file2");

                byte[] file1_data = Base64.decode(file1_encoded, Base64.DEFAULT);
                byte[] file2_data = Base64.decode(file2_encoded, Base64.DEFAULT);

                Bitmap bitmap1 = BitmapFactory.decodeByteArray(file1_data, 0, file1_data.length);
                Bitmap bitmap2 = BitmapFactory.decodeByteArray(file2_data, 0, file2_data.length);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        top_image.setImageBitmap(bitmap1);
                        bottom_image.setImageBitmap(bitmap2);
                    }
                });

                response.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }).start();

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ShowPhotoFragment.this)
                        .navigate(R.id.action_global_toHome);}
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}