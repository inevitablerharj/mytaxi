package com.mytaxi.android_demo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.mytaxi.android_demo.App;
import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.dependencies.component.AppComponent;
import com.mytaxi.android_demo.misc.Constants;
import com.mytaxi.android_demo.utils.Utility;
import com.mytaxi.android_demo.utils.network.HttpClient;
import com.mytaxi.android_demo.utils.storage.SharedPrefStorage;

import javax.inject.Inject;

import static com.mytaxi.android_demo.misc.Constants.LOG_TAG;

public class AuthenticationActivity extends AppCompatActivity {

    @Inject
    HttpClient mHttpClient;

    @Inject
    SharedPrefStorage mSharedPrefStorage;

    private EditText mEditTextUsername;
    private EditText mEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        AppComponent appComponent = App.getApplicationContext(this).getAppComponent();
        appComponent.inject(this);

        initializeControls();

    }

    private void initializeControls(){
        mEditTextUsername = findViewById(R.id.edt_username);
        mEditTextPassword = findViewById(R.id.edt_password);
        Button buttonLogin = findViewById(R.id.btn_login);
        buttonLogin.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void attemptLogin() {
        final String username = mEditTextUsername.getText().toString();
        final String password = mEditTextPassword.getText().toString();
        mHttpClient.fetchUser(Constants.RANDOM_USER_SEED, new HttpClient.UserCallback() {
            @Override
            public void run() {
                String sha256 = Utility.calculateSHA256(password, mUser.getSalt());
                if (mUser.match(username, sha256)) {
                    mSharedPrefStorage.saveUser(mUser);
                    finish();
                    Log.i(LOG_TAG, "Successful login with user: " + username);
                } else {
                    View view = findViewById(android.R.id.content);
                    Snackbar.make(view, R.string.message_login_fail, Snackbar.LENGTH_LONG).show();
                    Log.i(LOG_TAG, "Failed login with user: " + username);
                }
            }
        });
    }



}
