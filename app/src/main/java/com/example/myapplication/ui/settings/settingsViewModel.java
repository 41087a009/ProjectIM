package com.example.myapplication.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class settingsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public settingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is settings fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}