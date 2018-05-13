package com.example.android.registerandloginflow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by BIKASH THAKUR on 11-May-18.
 */

public class RegisterActivity extends AppCompatActivity {

    private Button mButtonRegister;
    private MaterialEditText mEditTextUsername;
    private MaterialEditText mEditTextUserEmail;
    private MaterialEditText mEditTextUserPassword;
    private MaterialEditText mEditTextUserConfirmPassword;
    private TextView mTextViewLoginAction;
    private Toolbar mToolbar;

    private String mUsernameKey;
    private String mUserEmailKey;
    private String mUserPasswordKey;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mToolbar = (Toolbar) findViewById(R.id.app_bar_top_register);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("Register");
        //back navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = RegisterActivity.this;

        mUsernameKey = getString(R.string.shared_pref_username_key);
        mUserEmailKey = getString(R.string.shared_pref_user_email_key);
        mUserPasswordKey = getString(R.string.shared_pref_user_password_key);

        mButtonRegister = (Button) findViewById(R.id.button_register);
        mEditTextUsername = (MaterialEditText) findViewById(R.id.et_register_username);
        mEditTextUserEmail = (MaterialEditText) findViewById(R.id.et_register_email);
        mEditTextUserPassword = (MaterialEditText) findViewById(R.id.et_register_password);
        mEditTextUserConfirmPassword = (MaterialEditText) findViewById(R.id.et_register_password_confirm);
        mTextViewLoginAction = (TextView) findViewById(R.id.tv_action_login);

        mTextViewLoginAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class loginActivity = LoginActivity.class;
                Intent intent = new Intent(mContext, loginActivity);
                startActivity(intent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = getUsername();
                String email = getUserEmail();
                String password = getUserPassword();
                String confirmPassword = getUserConfirmPassword();

                boolean isFieldsValid = validateFields(username, email, password, confirmPassword);

                if (isFieldsValid) {

                    saveUserDetails(username, email, password);

                    Class userProfileActivity = UserProfile.class;
                    Intent intent = new Intent(mContext, userProfileActivity);
                    intent.putExtra(intent.EXTRA_TEXT, "fromRegisterActivity");
                    intent.putExtra(intent.EXTRA_USER, getUsername());
                    startActivity(intent);
                }
            }
        });

    }

    private boolean validateFields(String username, String email, String password, String confirmPassword) {

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(mContext, "All fields are required.", Toast.LENGTH_LONG).show();
            return false;
        } else if (!confirmPassword.equals(password)) {
            Toast.makeText(mContext, "Passwords must match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private void saveUserDetails(String username, String email, String password) {

        SharedPreferences pref = getSharedPreferences(getString(R.string.shared_pref_name), MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(mUsernameKey, username);
        editor.putString(mUserEmailKey, email);
        editor.putString(mUserPasswordKey, password);

        editor.apply();
    }

    private String getUsername() {
        return mEditTextUsername.getText().toString().trim();
    }

    private String getUserEmail() {
        return mEditTextUserEmail.getText().toString().trim();
    }

    private String getUserPassword() {
        return mEditTextUserPassword.getText().toString().trim();
    }

    private String getUserConfirmPassword() {
        return mEditTextUserConfirmPassword.getText().toString().trim();
    }
}
