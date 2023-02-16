package com.example.fashionapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class SharedViewModel extends AndroidViewModel {
    MutableLiveData<String> diagnosisresult = new MutableLiveData<>();
    MutableLiveData<String> genderresult = new MutableLiveData<>();

    public SharedViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getDiagnosisResult(){
        return diagnosisresult;
    }
    public LiveData<String> getGenderResult(){
        return genderresult;
    }

    public void setDiagnosisResult(String result){
        diagnosisresult.postValue(result);
    }

    public void setGenderResult(String result){
        genderresult.postValue(result);
    }

}
