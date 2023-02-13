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

    public LiveData<List<String>> getKeyList() {
        return keywordList;
    }
    public List<String> getFirstKeys()
    {
        return firstKeys;
    }
    //처음에만
    public void setFirstKeyList(List<String> keywords) {
        firstKeys = new ArrayList<>(keywords);
//        keyList.postValue(keywords);
        resetkeyword(keywords);
    }

    //초기화
    public void resetkeyword(List<String> keys) {
        keywordList.postValue(new ArrayList<>(keys));
//        keywordList.postValue(keys);
    }

    public LiveData<String> chooseKeyword() {
        Random rand = new Random();
        List<String> keygroup = keywordList.getValue();
        int idx = rand.nextInt(keygroup.size());
        keyword.postValue(keygroup.get(idx));
        keygroup.remove(idx);
        if (keygroup.size() == 0) {
            resetkeyword(firstKeys);
        } else
            keywordList.postValue(keygroup);

        return keyword;
    }
}
