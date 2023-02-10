package com.example.fashionapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class SharedViewModel extends AndroidViewModel {
    MutableLiveData<String> diagnosisresult = new MutableLiveData<>();

    public SharedViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getResult(){
        return diagnosisresult;
    }
    public void setResult(String result){

        diagnosisresult.postValue(result);
    }

    //앱 종료 또는 앱 시작 시에 초기화 진행
    public void initResult(){
        diagnosisresult.postValue("");
    }
}
