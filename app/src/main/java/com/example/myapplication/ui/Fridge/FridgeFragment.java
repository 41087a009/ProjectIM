package com.example.myapplication.ui.Fridge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentFridgeBinding;

public class FridgeFragment extends Fragment {

    private FragmentFridgeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // * 1.Android 框架可以管理介面控制器的生命週期。如果系統銷毀
        // 或重新創建介面控制器，則存儲在其中的任何瞬態介面相關數據都會丟失。 *
        // * 2.ViewModel從介面控制器邏輯中分離出視圖數據擁有權的操作更容易且更高效。 *
        // https://developer.android.com/topic/libraries/architecture/viewmodel
        FridgeViewModel fridgeViewModel =
                new ViewModelProvider(this).get(FridgeViewModel.class);

        binding = FragmentFridgeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 提出在 ViewModel 中的資料
//        final TextView textView = binding.txvOutput;
//        final EditText editText = binding.edtInput;
//        fridgeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;

    }

//    private void initListener(){
//        Button btn=binding.button;
//
//    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}