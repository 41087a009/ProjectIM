package com.example.myapplication.ui.Fridge;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentAddingFoodsBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// BottomSheetDialogFragment is a version of DialogFragment.
public class AddingFoodsFragment extends BottomSheetDialogFragment {
    private FirebaseDatabase database;
    private DatabaseReference myRef,mDatabase;
    private FragmentAddingFoodsBinding binding;

    // 覆寫 onCreateView  連結 layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_adding_foods, container, false);
    }

    public void initView() {

    }

    // 連接 Firebase - Realtime Database & Storage
    public void set_Reference() {
        // 檢索數據庫實例並引用您要寫入的位置。
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("foods");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        myRef = FirebaseDatabase.getInstance().getReference();
    }
}