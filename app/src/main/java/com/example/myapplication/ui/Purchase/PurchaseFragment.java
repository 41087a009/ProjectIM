package com.example.myapplication.ui.Purchase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentPurchaseBinding;

public class PurchaseFragment extends Fragment {

    private FragmentPurchaseBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PurchaseViewModel settingsViewModel =
                new ViewModelProvider(this).get(PurchaseViewModel.class);

        binding = FragmentPurchaseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}