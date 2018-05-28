package com.example.ngocmanh.myappchat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Toolbar toolbar;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        mAuth = FirebaseAuth.getInstance();

        checkUser();
    }

    private void checkUser() {
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null)
        {
            startActivity(new Intent(MainActivity.this, StartActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "ahihi", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }



    private void init() {
        /*tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);
        edUser.setText("ngocmanh.dev@gmail.com");
        edPass.setText("manhvagiang123");*/

        toolbar = findViewById(R.id.toolBar_main);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chatting with me :3");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.setting_main:
                break;
            case R.id.alluser_main:
                break;
            case R.id.logout_main:
                FirebaseAuth.getInstance().signOut();
                checkUser();
                break;
        }

        return true;
    }
}
