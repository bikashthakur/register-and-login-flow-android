package com.example.android.registerandloginflow;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by BIKASH THAKUR on 13-May-18.
 */

public class ForgotPassword extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button mButtonSendEmail;
    private MaterialEditText mPasswordResetEmail;
    private String mUserEmailPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mToolbar = (Toolbar) findViewById(R.id.app_bar_top);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("Forgot Password");
        //back navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences pref = getSharedPreferences(getString(R.string.shared_pref_name), MODE_PRIVATE);

        mUserEmailPref = pref.getString(getString(R.string.shared_pref_user_email_key), null);

        mButtonSendEmail = (Button) findViewById(R.id.btn_send_email);
        mPasswordResetEmail = (MaterialEditText) findViewById(R.id.et_email_password_reset);

        mButtonSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mPasswordResetEmail.getText().toString().trim().equals(mUserEmailPref)) {
                    Toast.makeText(ForgotPassword.this, "This email is not registered.", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(ForgotPassword.this, "Check your mail.", Toast.LENGTH_SHORT).show();
                    mButtonSendEmail.setText("Resend");
                }

            }
        });

    }
}
