package com.mytaxi.android_demo.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.activities.DriverProfileActivity;
import com.mytaxi.android_demo.models.Driver;

public class AppNavigator {

    private final Activity activity;
    public AppNavigator(Activity activity){
        this.activity = activity;
    }

    public Intent createIntent(Driver driver, String key) {
        Intent intent = new Intent(activity, DriverProfileActivity.class);
        intent.putExtra(key, driver);
        return intent;
    }

    public Intent navigateToAuthenticationActivity() {
        return new Intent(activity, AuthenticationActivity.class);
    }
}
