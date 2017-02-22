package com.bruce.imageloder;

import android.graphics.Bitmap;

/**
 * Created by bruce on 2017/2/22.
 */

public class DiskCache implements ImageCache {
    @Override
    public Bitmap get(String imgUrl) {
        return null;
    }

    @Override
    public void put(String imgUrl, Bitmap bitmap) {

    }
}
