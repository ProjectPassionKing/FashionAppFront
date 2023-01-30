package com.example.fashionapp;

import static android.app.Activity.RESULT_OK;

import static org.apache.commons.io.FileUtils.readFileToByteArray;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.fashionapp.databinding.FragmentShowPhotoBinding;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShowPhotoFragment extends Fragment {

    private FragmentShowPhotoBinding binding;
    Button camera_open_id;
    ImageView click_image_id;
    TextView prediction;

    ActivityResultLauncher<Intent> activityResultLauncher;

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

        camera_open_id = getView().findViewById(R.id.camera_button);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
                    click_image_id.setImageBitmap(photo);
                }
            }
        });

        prediction = getView().findViewById(R.id.prediction);

        new Thread(() ->
        {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

            StringRequest request = new StringRequest(Request.Method.POST, "https://fashionback.azurewebsites.net/predict",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject((String) response);
                                String data = jsonObject.getString("prediction");
                                prediction.setText(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // handle error
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("file", encoded);
                    return params;
                }
            };

            RequestQueue queue = Volley.newRequestQueue(getContext());
            queue.add(request);
        }).start();

        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ShowPhotoFragment.this)
                        .navigate(R.id.action_ShowPhotoFragment_to_MainFragment);
            }
        });
        binding.menuHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ShowPhotoFragment.this)
                        .navigate(R.id.action_ShowPhotoFragment_to_AllinOneFragment);
            }
        });
        binding.cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(camera_intent);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}