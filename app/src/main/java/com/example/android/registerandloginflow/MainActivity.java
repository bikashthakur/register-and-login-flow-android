package com.example.android.registerandloginflow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnRegister = (Button) findViewById(R.id.btn_signup);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = MainActivity.this;
                Class loginActivity = LoginActivity.class;
                Intent intent = new Intent(context, loginActivity);
                startActivity(intent);
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = MainActivity.this;
                Class registerActivity = RegisterActivity.class;
                Intent intent = new Intent(context, registerActivity);
                startActivity(intent);
            }
        });

    }

    private void verifyLoginCredentials(String username, String password) {

    }
}
