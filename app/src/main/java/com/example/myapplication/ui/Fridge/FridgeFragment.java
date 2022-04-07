package com.example.myapplication.ui.Fridge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentFridgeBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FridgeFragment extends Fragment {

    private FragmentFridgeBinding binding;
    private List<Foods> foodsList = new ArrayList<>();
    Button btn_freeze;
    RecyclerView rc_foods;
    FloatingActionButton fb_AddingFoods;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // * 1.Android 框架可以管理介面控制器的生命週期。如果系統銷毀
        // 或重新創建介面控制器，則存儲在其中的任何瞬態介面相關數據都會丟失。 *
        // * 2.ViewModel 從介面控制器邏輯中分離出視圖數據擁有權的操作更容易且更高效。 *
        // https://developer.android.com/topic/libraries/architecture/viewmodel
        FridgeViewModel fridgeViewModel =
                new ViewModelProvider(this).get(FridgeViewModel.class);

        binding = FragmentFridgeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initView();
        initListener();

        forRC();

        return root;
    }

    private void initView() {
        btn_freeze = binding.button11;
        rc_foods = binding.rcFoods;
        fb_AddingFoods = binding.floatingActionButton2;
    }

    public void initListener() {
        // 點擊懸浮按鈕 - 新增食材的對話框
        fb_AddingFoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // 創建物件，並調用 show方法
                AddingFoodsFragment bottomSheet = new AddingFoodsFragment();
                bottomSheet.show(requireActivity().getSupportFragmentManager(), "AddFoods");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // 製作 Recycler 食材清單
    public void forRC() {
        // 放測試物件入list
        for (int i = 0; i < 50; i++) {
            Foods foods = new Foods();

            foods.setName("食材" + i);
            foods.setDate("日期" + i);
            foods.setAmount("數量" + i);

            foodsList.add(foods);
        }
//        Log.v("test", foodsList.get(2).getName());
//        Log.v("","已經加入資料");
        // 連接Adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rc_foods.setLayoutManager(linearLayoutManager);

        RCAdapter rcAdapter = new RCAdapter(foodsList);
        rc_foods.setAdapter(rcAdapter);
        Log.v("", "RC結束");

    }
}