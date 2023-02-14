package com.example.fashionapp.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchKeywordViewModel extends AndroidViewModel {
    private List<String> firstKeys; //넘어오는 거(처음에 받은 거 그대로 유지)
    MutableLiveData<List<String>> keywordList = new MutableLiveData<>(); //검색 후 요소 빠진 리스트
    MutableLiveData<String> keyword = new MutableLiveData<>();

    public SearchKeywordViewModel(@NonNull Application application) {
        super(application);
    }

    //처음에만
    public void setFirstKeyList(List<String> keywords) {
        firstKeys = new ArrayList<>(keywords);
        keywordList.postValue(new ArrayList<>(keywords));
    }

    public LiveData<String> chooseKeyword() {
        Random rand = new Random();
        List<String> keygroup = keywordList.getValue();
        int idx = rand.nextInt(keygroup.size());
        keyword.setValue(keygroup.get(idx));
        keygroup.remove(idx);
        if (keygroup.size() == 0) {
            keygroup = new ArrayList<>(firstKeys);
            keygroup.remove(keyword.getValue());
        }
        keywordList.setValue(keygroup);

        return keyword;
    }
}
