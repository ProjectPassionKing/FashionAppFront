package com.example.fashionapp.View;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fashionapp.R;
import com.example.fashionapp.databinding.FragmentShowSimBinding;

import java.io.File;
import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShowSimFragment extends Fragment {

    private FragmentShowSimBinding binding;
    ImageView sim_image_id;

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentShowSimBinding.inflate(inflater, container, false);
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
        sim_image_id = getView().findViewById(R.id.sim_image);
        sim_image_id.setImageBitmap(bitmap);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
                    sim_image_id.setImageBitmap(photo);
                }
            }
        });

        File finalMostRecentFile = mostRecentFile;

        new Thread(() ->
        {
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file","file.jpg", RequestBody.create(finalMostRecentFile, MultipartBody.FORM))
                    .build();

            Request request = new Request.Builder()
                    .url("http://172.23.247.89:5000/sim")
                    .post(requestBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String responseBodyString = response.body().string();
                System.out.println(responseBodyString);

                byte[] decodedString = Base64.decode(responseBodyString, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sim_image_id.setImageBitmap(decodedByte);
                    }
                });

                response.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ShowSimFragment.this)
                        .navigate(R.id.action_global_toHome);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}