package com.example.myapplication;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register,forgotPassword;
    private EditText et_account, et_password;
    private Button bt_login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        initView();

        check_loginState();
    }
    public void initView(){
        register = (TextView) findViewById(R.id.tv_register);
        register.setOnClickListener(this);

        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);

        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);

        mAuth = FirebaseAuth.getInstance();

        forgotPassword = (TextView) findViewById(R.id.tv_forgotpassword);
        forgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.bt_login:
                userLogin();
                break;

            case R.id.tv_forgotpassword:
                startActivity(new Intent(this,ForgotPassword.class));
                break;
        }
    }

    private void userLogin() {
        String email = et_account.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        // 判斷使用者輸入是否有效
        if (email.isEmpty()) {
            et_account.setError("電子信箱欄位必須填寫!!");
            et_account.requestFocus();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_account.setError("請提供有效的電子信箱!!");
            et_account.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            et_password.setError("電子信箱欄位必須填寫!!");
            et_password.requestFocus();
            return;
        }

        if (password.length() < 6) {
            et_password.setError("密碼至少要6個字元!!");
            et_password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "請檢查您的電子郵件，以驗證您的帳戶!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "登入失敗!請確認帳號密碼是否錯誤!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    // 檢查是否已經登入
    private void check_loginState(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            finish();
        }
    }

}
