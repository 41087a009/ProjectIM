package com.example.myapplication.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSettingsBinding;

public class settingsFragment extends Fragment {

    // * 視圖綁定 *
    // 每个绑定类均包含对根视图以及具有 ID 的所有视图的引用。
    // getRoot() 方法，用于为相应布局文件的根视图提供直接引用。
    private FragmentSettingsBinding binding;
    Button goAddRecipe;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(settingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initView();
        initListener();

        return root;
    }

    private void initView(){
        goAddRecipe = binding.buttonShare;
    }

    private void initListener(){
        // 使用上可以直接 binding.(View 的 id)
        // 可以不用一一連接View
        goAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_settings_to_addRecipeFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}