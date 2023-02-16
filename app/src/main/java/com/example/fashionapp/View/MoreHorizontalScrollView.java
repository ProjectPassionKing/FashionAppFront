package com.example.fashionapp.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fashionapp.R;

import org.jetbrains.annotations.NotNull;

//@SuppressLint("ViewConstructor")
public class MoreHorizontalScrollView extends LinearLayout {

    public MoreHorizontalScrollView(Fragment fragment) {
        super(fragment.getContext());
        init(fragment);
    }

    public MoreHorizontalScrollView(Fragment fragment, @Nullable AttributeSet attrs) {
        super(fragment.getContext(), attrs);
        init(fragment);
    }

    private void init(@NotNull Fragment fragment) {
        //context null 처리 나중에
        LayoutInflater inflater = (LayoutInflater) fragment.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.scroll, this, true);
        changeTxt(fragment);

        findViewById(R.id.more_request_btn1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnTxt = (String) ((Button) (findViewById(R.id.more_request_btn1))).getText();
                changeFragment(fragment, btnTxt);

            }
        });

        findViewById(R.id.more_request_btn2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnTxt = (String) ((Button) (findViewById(R.id.more_request_btn2))).getText();
                changeFragment(fragment, btnTxt);
            }
        });

        findViewById(R.id.more_request_btn3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnTxt = (String) ((Button) (findViewById(R.id.more_request_btn3))).getText();
                changeFragment(fragment, btnTxt);
            }
        });

    }

    private void changeTxt(Fragment fragment) {

        if(fragment.getClass() == ResultFragment.class) {
            ((Button) findViewById(R.id.more_request_btn1)).setText(getTxtfromStr(R.string.more_req_cordi));
            ((Button) findViewById(R.id.more_request_btn3)).setText(getTxtfromStr(R.string.more_req_diagnosis));

        } if(fragment.getClass() == RecommandFragment.class) {
            ((Button) findViewById(R.id.more_request_btn1)).setText(getTxtfromStr(R.string.more_req_photo));
            ((Button) findViewById(R.id.more_request_btn2)).setText(getTxtfromStr(R.string.more_req_detail));
        }if(fragment.getClass() == CordiTipFragment.class){
            ((Button) findViewById(R.id.more_request_btn3)).setText(getTxtfromStr(R.string.more_req_detail));

        }
    }

    private void changeFragment(Fragment fragment, String buttonTxt) {

        if (buttonTxt.equals(getTxtfromStr(R.string.more_req_cordi))) {
            navFragment(fragment, R.id.action_global_CordiTipFragment);
        }
        if (buttonTxt.equals(getTxtfromStr(R.string.more_req_detail))) {
            navFragment(fragment, R.id.action_global_ResultFragment);
        }
        if (buttonTxt.equals(getTxtfromStr(R.string.more_req_diagnosis))) {
            navFragment(fragment, R.id.action_global_DiagnosisGenderFragment);
        }
        if (buttonTxt.equals(getTxtfromStr(R.string.more_req_photo))) {
            navFragment(fragment, R.id.action_global_TakePhotoFragment);
        }
    }

    private String getTxtfromStr(int rString) {
        return this.getResources().getString(rString);
    }

    private void navFragment(Fragment fragment, int action) {
        NavHostFragment.findNavController(fragment).navigate(action);
    }
}

