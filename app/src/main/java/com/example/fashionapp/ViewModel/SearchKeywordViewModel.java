package com.example.fashionapp.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import java.util.Random;

public class SearchKeywordViewModel extends AndroidViewModel {
    List<String> firsetKeys; //넘어오는 거(처음에 받은 거 그대로 유지)
    MutableLiveData<List<String>> keyList = new MutableLiveData<>(); //초기값
    MutableLiveData<List<String>> keywordList = new MutableLiveData<>(); //검색 후 요소 빠진 리스트
    MutableLiveData<String> keyword = new MutableLiveData<>();

    public SearchKeywordViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<String>> getKeyList(){
        return keywordList;
    }

    //처음의 키워드 리스트 가져오기
    public LiveData<List<String>> getFirstkeys(){
        return keyList;
    }

    //처음에만
    public LiveData<List<String>> setFirstKeyList(List<String> keywords){
//        firsetKeys = new ArrayList<>(keywords);
        firsetKeys = keywords;
        keyList.postValue(keywords);
        resetkeyword(keywords);

        return keyList;
}

//초기화
    public void resetkeyword(List<String> keys){
        keywordList.postValue(keys);
    }

    public LiveData<String> chooseKeyword(){
        if (keywordList.getValue().size()==0){
            resetkeyword(keyList.getValue());
        }
        Random rand = new Random();
        int idx = rand.nextInt(keywordList.getValue().size());
        List<String> keygroup = keywordList.getValue();
        keyword.setValue(keygroup.remove(idx));
//        keygroup.remove(idx);
        keywordList.postValue(keygroup);
        return keyword;
    }
}
