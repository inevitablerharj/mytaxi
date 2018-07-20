package com.mytaxi.android_demo.misc;

import com.google.android.gms.maps.model.LatLng;

public final class Constants {

    private Constants() {
    }

    // Tag
    public static final String LOG_TAG = "MYT";

    // Network
    public static final int SOCKET_TIMEOUT = 30;

    // Location
    public static final float DEFAULT_ZOOM = 18.0f;
    public static final LatLng DEFAULT_LOCATION = new LatLng(53.544604, 9.928757); // mytaxi Hamburg office

    public static final String RANDOM_USER_SEED = "a1f30d446f820665";

    public static final String RANDOM_USER_URL = "https://randomuser.me/api/";

}
