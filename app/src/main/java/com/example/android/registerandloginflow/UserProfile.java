package com.example.android.registerandloginflow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by BIKASH THAKUR on 11-May-18.
 */

public class UserProfile extends AppCompatActivity {

    private TextView mTextViewUserWelcomeMesssage;
    private TextView mTextViewUserEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setSupportActionBar((Toolbar) findViewById(R.id.app_bar_top));
        getSupportActionBar().setTitle("Profile");

        mTextViewUserWelcomeMesssage = (TextView) findViewById(R.id.user_welcome_message);
        mTextViewUserEmail = (TextView) findViewById(R.id.user_email);
        Intent intent = getIntent();
        if (intent.hasExtra(intent.EXTRA_TEXT)) {
            String prevActivity = intent.getStringExtra(intent.EXTRA_TEXT);
            String username = intent.getStringExtra(intent.EXTRA_USER);

            SharedPreferences pref = getSharedPreferences(getString(R.string.shared_pref_name), MODE_PRIVATE);

            switch (prevActivity) {

                case "fromLoginActivity":
                    String welcomeMsg = "Hello " + username + ", Welcome back!";
                    mTextViewUserWelcomeMesssage.setText(welcomeMsg);
                    String mail = pref.getString(getString(R.string.shared_pref_user_email_key), null);
                    mTextViewUserEmail.setText(mail);
                    break;

                case "fromRegisterActivity":
                    String welcomeMssg = "Hello " + username + ", Welcome!";
                    mTextViewUserWelcomeMesssage.setText(welcomeMssg);
                    String umail = "Email: " + pref.getString(getString(R.string.shared_pref_user_email_key), null);
                    mTextViewUserEmail.setText(umail);
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_action_logout) {
            Context context = UserProfile.this;
            Class mainActivity = MainActivity.class;
            Intent intent = new Intent(context, mainActivity);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
