package com.example.ngocmanh.myappchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangKyActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextInputLayout reg_name, reg_user, reg_pass;
    Button btn;
    FirebaseAuth mAuth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        init();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(DangKyActivity.this)
                ;
                dialog.setMessage("Vui lòng đợi hệ thống xác nhận!!");
                dialog.show();
               // Log.e("aaa", reg_name.getEditText().getText().toString() + " , ");
                mAuth.createUserWithEmailAndPassword(reg_user.getEditText().getText().toString(), reg_pass.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            dialog.dismiss();
                            startActivity(new Intent(DangKyActivity.this, MainActivity.class));
                            Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(DangKyActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            dialog.hide();
                        }
                    }
                });
            }
        });


    }

    private void init() {
        reg_name = findViewById(R.id.reg_name);
        reg_user = findViewById(R.id.reg_user);
        reg_pass = findViewById(R.id.reg_pass);
        btn = findViewById(R.id.btnDangKy);
        mAuth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.toolBar_dangky);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Create account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
