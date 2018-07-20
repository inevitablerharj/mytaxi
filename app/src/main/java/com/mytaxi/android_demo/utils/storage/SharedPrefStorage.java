package com.mytaxi.android_demo.utils.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.mytaxi.android_demo.misc.Constants;
import com.mytaxi.android_demo.models.User;

public class SharedPrefStorage implements Storage {

    private SharedPreferences mSharedPref;

    public SharedPrefStorage(Context context) {
        this.mSharedPref = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public User loadUser() {
        String username = mSharedPref.getString(Constants.USERNAME, null);
        String salt = mSharedPref.getString(Constants.SALT, null);
        String sha256 = mSharedPref.getString(Constants.SHA_256, null);
        if (username != null && salt != null && sha256 != null) {
            return new User(username, salt, sha256);
        } else {
            return null;
        }
    }

    @Override
    public void saveUser(User user) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(Constants.USERNAME, user.getUsername());
        editor.putString(Constants.SALT, user.getSalt());
        editor.putString(Constants.SHA_256, user.getSHA256());
        editor.apply();
    }

    @Override
    public void resetUser() {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.remove(Constants.USERNAME);
        editor.remove(Constants.SALT);
        editor.remove(Constants.SHA_256);
        editor.apply();
    }

}
