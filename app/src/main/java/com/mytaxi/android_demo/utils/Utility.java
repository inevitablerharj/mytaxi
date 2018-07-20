package com.mytaxi.android_demo.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utility {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String calculateSHA256(String password, String salt) {
        String passwordWithSalt = password + salt;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(passwordWithSalt.getBytes(StandardCharsets.UTF_8));
            return String.format("%064x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return passwordWithSalt;
    }
}
