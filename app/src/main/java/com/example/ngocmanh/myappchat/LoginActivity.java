package com.example.ngocmanh.myappchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button btn;
    ProgressDialog dialog;
    FirebaseAuth mAuth;
    TextInputLayout user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setMessage("Vui lòng hệ thống kiểm tra!!");
                dialog.show();
                mAuth.signInWithEmailAndPassword(user.getEditText().getText().toString(), pass.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("aaa", user.getEditText().toString() + " , " + pass.getEditText().toString());
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            dialog.dismiss();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            dialog.hide();
                        }
                    }
                });
            }
        });
    }

    private void init() {
        user = findViewById(R.id.log_user);
        pass = findViewById(R.id.log_pass);
        btn = findViewById(R.id.btnDangNhap_login);
        mAuth = FirebaseAuth.getInstance();
    }
}
