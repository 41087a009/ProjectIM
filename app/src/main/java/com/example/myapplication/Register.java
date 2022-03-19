package com.example.myapplication;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView bt_register;
    private EditText et_emailadress, et_name, et_setpassword, et_confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        bt_register = (Button) findViewById(R.id.bt_register);
        bt_register.setOnClickListener(this);

        et_name = (EditText) findViewById(R.id.et_name);
        et_emailadress = (EditText) findViewById(R.id.et_emailadress);
        et_setpassword = (EditText) findViewById(R.id.et_setpassword);
        et_confirmpassword = (EditText) findViewById(R.id.et_confirmpassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_register:
                register();
                break;
        }
    }

    private void register() {
        String email = et_emailadress.getText().toString().trim();      // trim()去除掉字符串前後空白
        String setpassword = et_setpassword.getText().toString().trim();
        String name = et_name.getText().toString().trim();
        String confirmpassword = et_confirmpassword.getText().toString().trim();

        // 判斷使用者輸入是否為有效
        if (name.isEmpty()) {
            et_name.setError("暱稱欄位必須填寫!!");
            et_name.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            et_emailadress.setError("電子信箱欄位必須填寫!!");
            et_emailadress.requestFocus();
            return;
        }

        if (setpassword.isEmpty()) {
            et_setpassword.setError("設定密碼欄位必須填寫!!");
            et_setpassword.requestFocus();
            return;
        }

        if (confirmpassword.isEmpty()) {
            et_confirmpassword.setError("確認密碼欄位必須填寫!!");
            et_confirmpassword.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_emailadress.setError("請提供有效的電子信箱!!");
            et_emailadress.requestFocus();
            return;
        }

        if (setpassword.length() < 6) {
            et_setpassword.setError("密碼至少要6個字元!!");
            et_setpassword.requestFocus();
            return;
        }

        if (setpassword.equals(confirmpassword) == false) {
            et_confirmpassword.setError("密碼不一致!!");
            et_confirmpassword.requestFocus();
            return;
        }

        // 調用 Firebase 註冊功能
        mAuth.createUserWithEmailAndPassword(email,setpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(name,email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this,"用戶已成功註冊!!",Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(Register.this,"用戶註冊失敗!!，請在嘗試一次",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(Register.this,"註冊失敗!!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
