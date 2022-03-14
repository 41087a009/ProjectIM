package com.example.myapplication.ui.Fridge;

import android.text.Editable;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FridgeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FridgeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}