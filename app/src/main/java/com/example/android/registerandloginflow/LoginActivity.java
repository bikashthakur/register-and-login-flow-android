package com.example.android.registerandloginflow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private MaterialEditText mEditTextUsername;
    private MaterialEditText mEditTextPassword;
    private TextView mTextViewForgotPassword;
    private TextView mTextViewRegisterAction;
    private Toolbar mToolbar;

    private String mUsernameKey;
    private String mUserPasswordKey;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mToolbar = (Toolbar) findViewById(R.id.app_bar_top_login);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("Login");
        //back navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBtnLogin = (Button) findViewById(R.id.button_login);
        mEditTextUsername = (MaterialEditText) findViewById(R.id.et_login_username);
        mEditTextPassword = (MaterialEditText) findViewById(R.id.et_login_password);
        mTextViewForgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        mTextViewRegisterAction = (TextView) findViewById(R.id.tv_action_register);

        mContext = LoginActivity.this;

        mUsernameKey = getString(R.string.shared_pref_username_key);
        mUserPasswordKey = getString(R.string.shared_pref_user_password_key);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inpUsername = getUsername();
                String inpPassword = getUserPassword();

                boolean isValidCredentials = verifyUserCredentials(inpUsername, inpPassword);

                if (isValidCredentials) {

                    Class userProfileActivity = UserProfile.class;
                    Intent intent = new Intent(mContext, userProfileActivity);
                    intent.putExtra(intent.EXTRA_TEXT, "fromLoginActivity");
                    intent.putExtra(intent.EXTRA_USER, inpUsername);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Username or password error, try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mTextViewRegisterAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Class registerActivity = RegisterActivity.class;
                Intent intent = new Intent(mContext, registerActivity);
                startActivity(intent);
            }
        });

        mTextViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Class forgotPasswordActivity = ForgotPassword.class;
                Intent intent = new Intent(mContext, forgotPasswordActivity);
                startActivity(intent);
            }
        });

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(mUsernameKey)) {
                mEditTextUsername.setText(savedInstanceState.getString(mUsernameKey));
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String username = getUsername();
        if (!username.isEmpty()) {
            outState.putString(mUsernameKey, username);
        }
    }

    private boolean verifyUserCredentials(String username, String password) {
        SharedPreferences pref = getSharedPreferences(getString(R.string.shared_pref_name), MODE_PRIVATE);
        String sharedPrefUsername = pref.getString(mUsernameKey, null);
        String sharedPrefUserPasword = pref.getString(mUserPasswordKey, null);

        if (sharedPrefUsername.equalsIgnoreCase(username) && sharedPrefUserPasword.equalsIgnoreCase(password))
            return true;

        return false;
    }

    private String getUsername() {
        return mEditTextUsername.getText().toString().trim();
    }

    private String getUserPassword() {
        return mEditTextPassword.getText().toString().trim();
    }

}